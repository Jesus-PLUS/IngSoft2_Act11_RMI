import java.rmi.Naming;
//import java.rmi.Scanner;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args){
        try{
            // cliente
            // referencia a la interfaz mediante el registro RMI
            RemoteInterface bank = (RemoteInterface) Naming.lookup("//localhost/Servidor BBVA");
            Scanner sc = new Scanner(System.in);

            // flujo
            System.out.println("--- BIENVENIDO A BBVA ---");
            bank.createCount("Jesus Avila", 12345, 1000.0);
            System.out.println("Cuenta creada para Jesus Avila.");

            System.out.print("Monto a depositar en cuenta 12345: ");
            double dep = sc.nextDouble();
            System.out.println(bank.deposit(12345, dep));

            System.out.print("Monto a retirar: ");
            double ret = sc.nextDouble();
            System.out.println(bank.withdraw(12345, ret));

        }catch (Exception e){
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
