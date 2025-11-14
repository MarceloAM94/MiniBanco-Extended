package banco.modelo;

import java.time.LocalDateTime;

public class Transaccion {

    private int id;
    private String tipoTransaccion;
    private double monto;
    private String descripcion;
    private LocalDateTime fecha;

    public Transaccion(int id, String tipoTransaccion, double monto, String descripcion, LocalDateTime fecha) {
        this.id = id;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "Id: " + id + " | Transaccion: " + tipoTransaccion + " | Monto: " + monto +
                " | Descripcion: " + descripcion + " | Fecha: " + fecha +
                '}';
    }
}
