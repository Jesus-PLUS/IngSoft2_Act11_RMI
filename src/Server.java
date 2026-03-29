import javax.swing.*;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

//----------------------------------------------------------------


public class Server {
    public static void main(String[] args){

        try{
            // cliente
            // referencia a la interfaz mediante el registro RMI
            RemoteInterface bank = new RemoteImpl();
            Naming.rebind("Servidor BBVA", bank);
            System.out.println("Servidor listo");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
