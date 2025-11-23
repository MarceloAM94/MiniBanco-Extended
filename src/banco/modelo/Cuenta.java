package banco.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Transaccion> depositar(double monto){
       if (monto <= 0){
           return Optional.empty();
       }else {
           double nuevoSaldo = getSaldo() + monto;
           setSaldo(nuevoSaldo);

           Transaccion transaccionRegistrada = registrarTransaccion("Deposito", monto, "Deposito en cajero.");

           return Optional.of(transaccionRegistrada);
       }
    }

    public Optional<Transaccion> retirar(double monto){
        if (monto <= 0 || monto > getSaldo()){
            return Optional.empty();
        }else {
            double nuevoSaldo = getSaldo() - monto;
            setSaldo(nuevoSaldo);

            Transaccion transaccionRegistrada = registrarTransaccion("Retiro", monto, "Retiro en cajero.");
            return Optional.of(transaccionRegistrada);
        }
    }

    public Transaccion registrarTransaccion(String tipoTransaccion, double monto, String descripcion){
        if(this.transaccion == null){
            this.transaccion = new ArrayList<>();
        }

        int id = this.transaccion.size() + 1;
        LocalDateTime ahora = LocalDateTime.now();

        Transaccion t = new Transaccion(id, tipoTransaccion, monto, descripcion, ahora);
        this.transaccion.add(t);
        return t;
    }

    public Optional<TransferenciaResultado> transferir(double monto, Cuenta destino){
        if(monto <= 0 || destino == null || monto > this.saldo){
            return Optional.empty();
        }

        // Retiramos el monto de la cuenta actual (origen)
        this.saldo -= monto;
        Transaccion t1 =  this.registrarTransaccion("Transferencia enviada", monto,
                "Transferencia a cuenta N° " + destino.getNroCuenta());

        //Depositamos el monto en la cuenta destino
        destino.saldo += monto;
        Transaccion t2 = destino.registrarTransaccion("Transferencia recibida", monto,
                "Transferencia desde cuenta N° " + this.getNroCuenta());

        TransferenciaResultado resultado = new TransferenciaResultado(t1, t2);

        return Optional.of(resultado);
    }

    public List<Transaccion> historial() {
        if (this.transaccion == null || this.transaccion.isEmpty()) {
            return List.of();
        }else {
            return List.copyOf(transaccion);
        }
    }

    @Override
    public String toString() {
        return """
        Cuenta {
            Número de cuenta: %d
            Titular: %s
            Saldo: S/. %.2f
            Tipo de cuenta: %s
            Transacciones registradas: %d
        }
        """.formatted(
                nroCuenta,
                cliente != null ? cliente.getNombre() : "Sin titular",
                saldo,
                tipoCuenta,
                transaccion != null ? transaccion.size() : 0
        );
    }

}
