package banco.vista;

import banco.modelo.*;
import banco.servicio.Banco;

import java.util.List;
import java.util.Optional;
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
                    8. Reporte general del Sistema
                    9. Salir del sistema
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
                    Optional<Cliente> cliente = banco.buscarCliente(dni);

                    if(cliente.isEmpty()){
                        System.out.println("No se encontro un cliente con ese DNI.");
                    }else {
                        System.out.println("Ingrese el tipo de cuenta (Ahorro / Corriente).");
                        String tipoCuenta = sc.nextLine();

                        Cuenta cuentaCreada = banco.crearCuenta(cliente.get(), tipoCuenta);

                        System.out.println("Cuenta creada exitosamente.");
                        System.out.println("Número de cuenta: " + cuentaCreada.getNroCuenta());
                        System.out.println("Titular: " + cliente.get().getNombre());
                        System.out.println("Tipo: " + tipoCuenta);
                        System.out.println();
                    }
                }

                case 3 -> {
                    System.out.print("Ingrese el número de cuenta donde desea realizar el depósito: ");
                    int numCuenta = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese el monto a depositar: ");
                    double monto = sc.nextDouble();
                    sc.nextLine();

                    banco.depositarEnCuenta(numCuenta, monto)
                            .ifPresentOrElse(
                                    trans -> {
                                        System.out.println("Depósito realizado con éxito.");
                                        System.out.println(trans);
                                    },
                                    () -> System.out.println("No se pudo realizar el depósito (cuenta inválida o monto incorrecto).")
                            );
                }

                case 4 -> {
                    System.out.print("Ingrese el número de cuenta donde desea realizar el retiro: ");
                    int numCuenta = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese el monto a retirar: ");
                    double monto = sc.nextDouble();
                    sc.nextLine();

                    banco.retirarDeCuenta(numCuenta, monto)
                            .ifPresentOrElse(
                                    trans -> {
                                        System.out.println("Retiro realizado con éxito.");
                                        System.out.println(trans);
                                    },
                                    () -> System.out.println("No se pudo realizar el retiro (saldo insuficiente o cuenta inválida).")
                            );
                }

                case 5 -> {
                    System.out.print("Ingrese el número de cuenta ORIGEN: ");
                    int nroCuentaOrigen = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ingrese el número de cuenta DESTINO: ");
                    int nroCuentaDestino = sc.nextInt();

                    System.out.println("Ingrese el monto a transferir:");
                    double monto = sc.nextDouble();
                    sc.nextLine();

                    banco.transferirEntreCuentas(nroCuentaOrigen, nroCuentaDestino, monto)
                            .ifPresentOrElse(
                                    resultado -> {
                                        System.out.println("Transferencia realizada con éxito.");
                                        System.out.println(resultado.enviada());
                                        System.out.println(resultado.recibida());
                                    },
                                    () -> System.out.println("La transferencia no se pudo realizar (cuentas inválidas o saldo insuficiente).")
                            );
                }

                case 6 -> {
                    System.out.print("Ingrese el número de cuenta: ");
                    int nroCuenta = sc.nextInt();
                    sc.nextLine();

                    banco.buscarCuenta(nroCuenta)
                            .ifPresentOrElse(
                                    cuenta -> {
                                        var historial = cuenta.historial();
                                        if (historial.isEmpty()) {
                                            System.out.println("No hay transacciones registradas.");
                                        } else {
                                            System.out.println("=== Historial de la cuenta " + nroCuenta + " ===");
                                            historial.forEach(System.out::println);
                                        }
                                    },
                                    () -> System.out.println("No se encontró una cuenta con ese número.")
                            );
                }

                case 7 -> {
                    System.out.println("=== Lista de Clientes ===");
                    banco.mostrarClientes()
                            .forEach(System.out::println);
                }

                case 8 -> {
                    ReporteGeneral r = banco.generarReporteGeneral();

                    System.out.println("===== REPORTE GENERAL DEL BANCO =====");
                    System.out.println("Total clientes: " + r.totalClientes());
                    System.out.println("Total cuentas: " + r.totalCuentas());
                    System.out.println("Saldo total: S/. " + r.saldoTotal());
                    System.out.println("Promedio por cuenta: S/. " + r.promedioSaldo());
                    System.out.println("Total transacciones: " + r.totalTransacciones());

                    System.out.println("\nCuenta con mayor saldo:");
                    System.out.println(r.cuentaMayorSaldo());

                    System.out.println("\nCuenta con menor saldo:");
                    System.out.println(r.cuentaMenorSaldo());

                    System.out.println("\nTop 5 cuentas por saldo:");
                    r.top5Cuentas().forEach(System.out::println);

                    System.out.println("\nCuentas agrupadas por tipo:");
                    r.cuentasPorTipo().forEach((tipo, cantidad) ->
                            System.out.println(tipo + ": " + cantidad)
                    );
                }
            }
        }while (opcion != 9);
    }
}
