import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServidorCalculadora extends UnicastRemoteObject implements ServicioCalculadora {
    public ServidorCalculadora() throws RemoteException {
        super();
    }

    public float sumar(float a, float b) throws RemoteException {
        return a + b;
    }

    public float restar(float a, float b) throws RemoteException {
        return a - b;
    }

    public float multiplicar(float a, float b) throws RemoteException {
        return a * b;
    }

    public float dividir(float a, float b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Error: División por cero");
        }
        return a / b;
    }
}