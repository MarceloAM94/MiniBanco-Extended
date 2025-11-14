package banco.vista;

import banco.modelo.Cliente;
import banco.modelo.Cuenta;
import banco.servicio.Banco;
import java.util.ArrayList;
import java.util.Scanner;

public class MainBanco {
    public static void main(String[] args) {

        Banco banco = new Banco();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("""
                    ======== MINI BANCO EXTENDED ========
                    1. Registrar cliente
                    2. Crear cuentas
                    3. Depositar dinero
                    4. Retirar dinero
                    5. Transferencia entre cuentas
                    6. Consultar historial
                    7. Mostrar clientes
                    8. Salir del sistema
                    """);
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();//Limpiar buffer

            switch (opcion){
                case 1 -> {
                    System.out.println("Ingrese su DNI: ");
                    String dni = sc.nextLine();

                    System.out.println("Ingrese nombre de cliente: ");
                    String nombre = sc.nextLine();

                    Cliente nuevo = new Cliente(dni, nombre);
                    banco.registrarCliente(nuevo);
                }

                case 2 -> {
                    System.out.println("Ingrese su DNI: ");
                    String dni = sc.nextLine();
                    Cliente cliente = banco.buscarCliente(dni);

                    if(cliente == null){
                        System.out.println("No se encontro un cliente con ese DNI.");
                    }else {
                        System.out.println("Ingrese el tipo de cuenta (Ahorro / Corriente).");
                        String tipoCuenta = sc.nextLine();

                        banco.crearCuenta(cliente, tipoCuenta);
                    }
                }

                case 3 -> {
                    System.out.print("Ingrese el numero de cuenta donde desea realizar el deposito: ");
                    int numCuenta = sc.nextInt();
                    sc.nextLine();
                    Cuenta cuenta = banco.buscarCuenta(numCuenta);

                    if (cuenta == null){
                        System.out.println("No se encontro una cuenta con ese numero.");
                    }else {
                        System.out.println("Ingrese el monto a depositar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();
                        cuenta.depositar(monto);
                    }
                }

                case 4 -> {
                    System.out.print("Ingrese el numero de cuenta donde desea realizar el retiro: ");
                    int numCuenta = sc.nextInt();
                    sc.nextLine();
                    Cuenta cuenta = banco.buscarCuenta(numCuenta);

                    if (cuenta == null){
                        System.out.println("No se encontro una cuenta con ese numero.");
                    }else {
                        System.out.println("Ingrese el monto a retirar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();
                        cuenta.retirar(monto);
                    }
                }

                case 5 -> {
                    System.out.print("Ingrese el numero de cuenta ORIGEN: ");
                    int nroCuentaOrigen = sc.nextInt();
                    sc.nextLine();
                    Cuenta origen = banco.buscarCuenta(nroCuentaOrigen);

                    if (origen == null) {
                        System.out.println("No se encontró una cuenta con ese número.");
                        break;
                    }

                    System.out.print("Ingrese el numero de cuenta DESTINO: ");
                    int nroCuentaDestino = sc.nextInt();
                    Cuenta destino = banco.buscarCuenta(nroCuentaDestino);

                    if (destino == null) {
                        System.out.println("No se encontró una cuenta destino con ese número.");
                        break;
                    }

                    System.out.println("Ingrese el monto a transferir:");
                    double monto = sc.nextDouble();
                    sc.nextLine();

                    origen.transferir(monto, destino);
                }

                case 6 -> {
                    System.out.println("Ingrese el numero de cuenta para consultar historial de transacciones.");
                    int nroCuenta = sc.nextInt();
                    sc.nextLine();
                    Cuenta cuenta = banco.buscarCuenta(nroCuenta);

                    if (cuenta == null){
                        System.out.println("No se encontro una cuenta con ese numero: ");
                    }else {
                        cuenta.mostrarHistorial();
                    }
                }

                case 7 -> {
                    System.out.println("=== Lista de Clientes ===");
                    banco.mostrarClientes();
                }
            }
        }while (opcion != 8);
    }
}
