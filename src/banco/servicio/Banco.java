package banco.servicio;

import banco.modelo.*;
import banco.util.GeneradorNumeroCuenta;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Banco{

    private String nombreBanco;
    private List<Cliente> clientes;

    public Banco() {
        this.nombreBanco = "Mini Banco Extendido";
        this.clientes = new ArrayList<>();
    }

    public Banco(String nombreBanco, List<Cliente> clientes) {
        this.nombreBanco = nombreBanco;
        this.clientes = clientes;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void registrarCliente(Cliente c){
        clientes.add(c);
    }

    public Optional<Cliente> buscarCliente (String dni){
        Optional<Cliente> clienteEncontrado = clientes.stream()
                .filter(n -> Objects.equals(dni, n.getDni()))
                .findFirst();
        return clienteEncontrado;
    }

    public Optional<Cuenta> buscarCuenta (int nroCuenta){
        Optional<Cuenta> cuentaEncontrada = clientes.stream()
                .flatMap(Cl -> Cl.getCuentas().stream())
                .filter(Cu -> Cu.getNroCuenta() == nroCuenta)
                .findFirst();
        return cuentaEncontrada;
    }

    public Cuenta crearCuenta(Cliente cliente, String tipoCuenta) {
        int numeroCuenta = GeneradorNumeroCuenta.generarNroCuenta();
        double saldoInicial = 0;

        Cuenta nuevaCuenta = new Cuenta(
                numeroCuenta,
                cliente,
                saldoInicial,
                tipoCuenta,
                new ArrayList<>()
        );

        cliente.getCuentas().add(nuevaCuenta);

        return nuevaCuenta;
    }

    public List<Cliente> mostrarClientes(){
        return List.copyOf(clientes);
    }

    public ReporteGeneral generarReporteGeneral(){
        //Obtener lista de todas las cuentas del banco
        List<Cuenta> todasLasCuentas = clientes.stream()
                .flatMap(cliente -> cliente.getCuentas().stream())
                .toList();

        //Total Clientes
        int totalClientes;
        if (clientes.isEmpty()){
            totalClientes = 0;
        }else {
            totalClientes = clientes.size();
        }

        //Total Cuentas
        int totalCuentas = todasLasCuentas.size();

        //Saldo Total del Banco
        double totalSaldo;
        if (todasLasCuentas.isEmpty()){
            totalSaldo = 0.0;
        }else {
            totalSaldo = todasLasCuentas.stream()
                    .mapToDouble(Cuenta::getSaldo)
                    .sum();
        }

        //Promedio de saldo por cuenta
        double promedioSaldo;
        if (todasLasCuentas.isEmpty()){
            promedioSaldo = 0.0;
        }else {
            promedioSaldo = totalSaldo / totalCuentas;
        }

        //Cuenta con mayor saldo
        Optional<Cuenta> mayorSaldo = todasLasCuentas.stream()
                .max(Comparator.comparingDouble(Cuenta::getSaldo));

        //Cuenta con menor saldo
        Optional<Cuenta> menorSaldo = todasLasCuentas.stream()
                .min(Comparator.comparingDouble(Cuenta::getSaldo));

        //Total transacciones
        int totalTransacciones = todasLasCuentas.stream()
                .mapToInt(cuenta -> cuenta.getTransaccion().size())
                .sum();

        //5 cuentas con mas saldo
        List<Cuenta> top5Cuentas = todasLasCuentas.stream()
                .sorted(Comparator.comparing(Cuenta::getSaldo).reversed())
                .limit(5)
                .toList();

        //Agrupacion por tipo de cuenta
        Map<String, Long> tiposCuenta = todasLasCuentas.stream()
                .collect(
                        Collectors.groupingBy(
                                Cuenta::getTipoCuenta,
                                Collectors.counting()
                        )
                );

        //Reporte General
        return new ReporteGeneral(
                totalClientes,
                totalCuentas,
                totalSaldo,
                promedioSaldo,
                mayorSaldo.orElse(null),
                menorSaldo.orElse(null),
                totalTransacciones,
                top5Cuentas,
                tiposCuenta
        );
    }

    public Optional<Transaccion> depositarEnCuenta(int nroCuenta, double monto){
        return buscarCuenta(nroCuenta)
                .flatMap(cuenta -> cuenta.depositar(monto));
    }

    public Optional<Transaccion> retirarDeCuenta(int nroCuenta, double monto){
        return buscarCuenta(nroCuenta)
                .flatMap(cuenta -> cuenta.retirar(monto));
    }

    public Optional<TransferenciaResultado> transferirEntreCuentas(int origen, int destino, double monto) {
        return buscarCuenta(origen)
                .flatMap(cOrigen -> buscarCuenta(destino)
                        .flatMap(cDestino -> cOrigen.transferir(monto, cDestino))
                );
    }
}
