// implementacion

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface{
    // 'java.rmi.server.UnicastRemoteObject'
    // implementa la interfaz
    // constructor de clase, invoca al constructor de la interfaz

    private HashMap<Integer, Cuenta> counts;
    public RemoteImpl() throws RemoteException {
        // invoca constructor de superclase
        super();
        counts = new HashMap<>();
    }

    @Override
    public void createCount(String userName, int userCount, double initialBalance) throws RemoteException{
        counts.put(userCount, new Cuenta(userName, userCount, initialBalance));
    }

    @Override
    public String deposit(int userCount, double amount) throws RemoteException {
        if (!counts.containsKey(userCount)) return "Cuenta inexistente";

        Cuenta c = counts.get(userCount);

        double prevBalance = c.getBalance();
        c.setBalance(prevBalance + amount);
        return "Depósito concretado \nSaldo anterior | " + prevBalance + "\nSaldo actual | " + c.getBalance();
    }

    @Override
    public String withdraw(int userCount, double amount) throws RemoteException {
        if (!counts.containsKey(userCount)) return "Error: Cuenta no existe.";

        Cuenta c = counts.get(userCount);
        if (c.getBalance() < amount) return "Error: Saldo insuficiente.";

        double prevBalance = c.getBalance();
        c.setBalance(prevBalance - amount);
        return "Retiro exitoso. Saldo Inicial: " + prevBalance + " | Saldo Final: " + c.getBalance();
    }
}
