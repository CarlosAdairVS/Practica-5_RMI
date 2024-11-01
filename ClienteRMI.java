/*
    Para correr el programa ejecutar el siguiente comando
    // java ClienteRMI.java [IP servidor]
    Necesario tener en cliente ServicioCalculadora.java
*/
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteRMI {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java ClienteRMI <IP_del_servidor>");
            System.exit(1);
        }

        try {
            String serverIP = args[0];
            String url = "rmi://" + serverIP + "/ServicioCalculadora";
            ServicioCalculadora calculadora = (ServicioCalculadora) Naming.lookup(url);
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Conectado al servidor: " + serverIP);
            
            while (true) {
                try {
                    System.out.println("\n=== Calculadora RMI ===");
                    System.out.println("Ingrese el primer número (o 'salir' para terminar):");
                    String input = scanner.nextLine();
                    
                    if (input.equalsIgnoreCase("salir")) {
                        break;
                    }
                    
                    float num1 = Float.parseFloat(input);
                    
                    System.out.println("Ingrese el segundo número:");
                    float num2 = Float.parseFloat(scanner.nextLine());
                    
                    System.out.println("\nResultados:");
                    System.out.println("Suma: " + calculadora.sumar(num1, num2));
                    System.out.println("Resta: " + calculadora.restar(num1, num2));
                    System.out.println("Multiplicación: " + calculadora.multiplicar(num1, num2));
                    
                    try {
                        System.out.println("División: " + calculadora.dividir(num1, num2));
                    } catch (RemoteException e) {
                        System.out.println("Error en división: " + e.getMessage());
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor ingrese números válidos.");
                }
            }
            
            scanner.close();
            System.out.println("¡Gracias por usar la calculadora!");
            
        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
