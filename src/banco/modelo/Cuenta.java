package banco.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private int nroCuenta;
    private Cliente cliente;
    private double saldo;
    private String tipoCuenta;
    private List<Transaccion> transaccion;

    public Cuenta(int nroCuenta, Cliente cliente, double saldo, String tipoCuenta, List<Transaccion> transaccion) {
        this.nroCuenta = nroCuenta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.transaccion = (transaccion != null) ? transaccion : new ArrayList<>();
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public List<Transaccion> getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(List<Transaccion> transaccion) {
        this.transaccion = transaccion;
    }

    public void depositar(double monto){
       if (monto <= 0){
           System.out.println("El monto debe ser mayor a 0.");
           return;
       }
       double nuevoSaldo = getSaldo() + monto;
       setSaldo(nuevoSaldo);

        registrarTransaccion("Deposito", monto, "Deposito en cajero.");
        System.out.println("Retiro realizado con exito");
    }

    public void retirar(double monto){
        if (monto <= 0){
            System.out.println("El monto debe ser mayor a 0.");
            return;
        }
        if (monto > getSaldo()){
            System.out.println("No puede retirar esta cantidad de dinero, su saldo actual es: S/." + getSaldo());
        }else {
            double nuevoSaldo = getSaldo() - monto;
            setSaldo(nuevoSaldo);

            registrarTransaccion("Retiro", monto, "Retiro en cajero.");
            System.out.println("Retiro realizado con exito");
        }
    }

    public void registrarTransaccion(String tipoTransaccion, double monto, String descripcion){
        if(this.transaccion == null){
            this.transaccion = new ArrayList<>();
        }

        int id = this.transaccion.size() + 1;
        LocalDateTime ahora = LocalDateTime.now();

        Transaccion t = new Transaccion(id, tipoTransaccion, monto, descripcion, ahora);
        this.transaccion.add(t);
    }

    public void transferir(double monto, Cuenta destino){
        if(monto <= 0){
            System.out.println("El monto debe ser mayor que 0.");
            return;
        }

        if(destino == null){
            System.out.println("La cuenta destino no existe.");
            return;
        }

        if (monto > this.saldo){
            System.out.println("Saldo insuficiente para realizar la transferencia.");
            return;
        }

        // Retiramos el monto de la cuenta actual (origen)
        this.saldo -= monto;
        this.registrarTransaccion("Transferencia enviada", monto,
                "Transferencia a cuenta N° " + destino.getNroCuenta());

        //Depositamos el monto en la cuenta destino
        destino.saldo += monto;
        destino.registrarTransaccion("Transferencia recibida", monto,
                "Transferencia desde cuenta N° " + this.getNroCuenta());

        //Mensajes de confirmacion
        System.out.println("Transferencia realizada con éxito.");
        System.out.println("Monto transferido: S/." + monto);
        System.out.println("Cuenta destino: " + destino.getNroCuenta());
        System.out.println("Saldo actual: S/." + this.saldo);
    }

    public void mostrarHistorial() {
        if (this.transaccion == null || this.transaccion.isEmpty()) {
            System.out.println("No hay transacciones registradas en esta cuenta.");
            return;
        }

        System.out.println("=== Historial de la cuenta " + this.nroCuenta + " ===");
        for (Transaccion t : this.transaccion) {
            System.out.println(t);
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "NroCuenta: " + nroCuenta +
                " | Titular: " + (cliente != null ? cliente.getNombre() : "Sin titular") +
                " | Saldo = S/." + saldo +
                " | TipoCuenta: '" + tipoCuenta + '\'' +
                '}';
    }
}
