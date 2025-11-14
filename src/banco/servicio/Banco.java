package banco.servicio;

import banco.modelo.Cliente;
import banco.modelo.Cuenta;
import banco.modelo.Transaccion;
import banco.util.GeneradorNumeroCuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        System.out.println("Cliente agregado con exito.");
        System.out.println();
    }

    public Cliente buscarCliente (String dni){
        if(clientes.isEmpty()){
            System.out.println("La lista de clientes esta vacia");
        }else {
            for (Cliente c : clientes){
                if(Objects.equals(dni, c.getDni())){
                    return c;
                }
            }
        }
        return null;
    }

    public Cuenta buscarCuenta (int nroCuenta){
        for (Cliente c : clientes){
            for (Cuenta cuenta : c.getCuentas()){
                if (cuenta.getNroCuenta() == nroCuenta){
                    return cuenta;
                }
            }
        }
        return null;
    }

    public void crearCuenta(Cliente cliente, String tipoCuenta){
        int numeroCuenta = GeneradorNumeroCuenta.generarNroCuenta();
        double saldo = 0;
        List<Transaccion> transacciones = new ArrayList<>();
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, cliente, saldo, tipoCuenta, null);
        cliente.getCuentas().add(nuevaCuenta);
        System.out.println("Cuenta creada exitosamente.");
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + cliente.getNombre());
        System.out.println("Tipo: " + tipoCuenta);
        System.out.println();
    }

    public void mostrarClientes(){
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados");
        }else{
            for (Cliente c : clientes){
                System.out.println(c);
            }
        }
    }

    public void generarReporteGeneral(){

    }
}
