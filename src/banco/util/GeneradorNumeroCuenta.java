package banco.util;

public class GeneradorNumeroCuenta {

    static int contador = 0;
    public static int generarNroCuenta(){
        contador++;
        return 1000 + contador;
    }
}
