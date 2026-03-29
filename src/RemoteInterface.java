// interfaz remota
// methods accesobles via remota, con parametros
// solo declara, no implementa
// 'extender java.rmi.Remote'

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    int createCount(String userName, double initialBalance) throws RemoteException;



    String deposit(int userCount, double amount) throws RemoteException;
    String withdraw(int userCount, double amount) throws RemoteException;


}
