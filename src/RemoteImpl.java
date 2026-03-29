// implementacion

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Random;

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
    public int createCount(String userName, double initialBalance) throws RemoteException{
        Random randCount = new Random();

        int genNumber = randCount.nextInt(90000) + 10000;
        counts.put(genNumber, new Cuenta(userName, genNumber, initialBalance));

        return genNumber;

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

    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cuentas.ser"))) {
            oos.writeObject(counts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

