package banco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String dni;
    private String nombre;
    private List<Cuenta> cuentas;
    //No se si falten atributos


    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return """
        Cliente {
            DNI: %s
            Nombre: %s
            Cantidad de cuentas: %d
        }
        """.formatted(
                dni,
                nombre,
                (cuentas != null ? cuentas.size() : 0)
        );
    }


    public void mostrarCuentas(){
        for (Cuenta c : cuentas){
            System.out.println(c);
        }
    }

    public Cuenta buscarCuenta(int nroCuenta){
        //no se
        return null;
    }

}
