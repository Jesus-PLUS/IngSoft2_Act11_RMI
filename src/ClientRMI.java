import java.rmi.Naming;
//import java.rmi.Scanner;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args){
        try{
            // cliente
            // referencia a la interfaz mediante el registro RMI
            RemoteInterface bank = (RemoteInterface) Naming.lookup("//localhost/Servidor BBVA");
            Scanner sc = new Scanner(System.in);

            // flujo
            int menu = 0;
            while (menu != 4) {
                try {
                    System.out.println("\n--- BIENVENIDO A BBVA (RMI) ---");
                    System.out.println("1. Crear Cuenta");
                    System.out.println("2. Realizar Depósito");
                    System.out.println("3. Realizar Retiro");
                    System.out.println("4. Salir");
                    System.out.print("Seleccione una opción: ");
                    menu = sc.nextInt();
                    sc.nextLine(); // Limpiar el buffer

                    switch (menu) {
                        case 1:
                            System.out.print("Nombre del titular: ");
                            String userName = sc.nextLine();
                            System.out.print("Saldo inicial: ");
                            double balance = sc.nextDouble();

                            int genNumber = bank.createCount(userName, balance);
                            System.out.println("¡Cuenta creada exitosamente!");
                            break;

                        case 2:
                            System.out.print("Número de cuenta: ");
                            int numDep = sc.nextInt();
                            System.out.print("Monto a depositar: ");
                            double montoDep = sc.nextDouble();

                            // Imprime la respuesta que viene del servidor
                            System.out.println("\n[Servidor]: " + bank.deposit(numDep, montoDep));
                            break;

                        case 3:
                            System.out.print("Número de cuenta: ");
                            int numRet = sc.nextInt();
                            System.out.print("Monto a retirar: ");
                            double montoRet = sc.nextDouble();

                            // Llamada al método withdraw (asegúrate de que esté en tu interfaz)
                            System.out.println("\n[Servidor]: " + bank.withdraw(numRet, montoRet));
                            break;

                        case 4:
                            System.out.println("Saliendo del sistema...");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Por favor, ingrese un valor numérico válido.");
                    sc.nextLine(); // Limpiar el buffer para evitar bucle infinito
                }
            }

        }catch (Exception e){
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
