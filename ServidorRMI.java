/*
    Para correr el programa ejecutar el siguiente comando
    // java ServidorRMI.java [IP servidor]
*/
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorRMI {
    public static void main(String[] args) {
        try {
            // Establecer la propiedad java.rmi.server.hostname
            if (args.length > 0) {
                System.setProperty("java.rmi.server.hostname", args[0]);
            } else {
                System.out.println("Uso: java ServidorRMI <IP_del_servidor>");
                System.exit(1);
            }

            ServicioCalculadora servicio = new ServidorCalculadora();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//0.0.0.0/ServicioCalculadora", servicio);
            System.out.println("Servidor de Calculadora RMI iniciado en " + args[0]);
            System.out.println("Esperando conexiones...");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}