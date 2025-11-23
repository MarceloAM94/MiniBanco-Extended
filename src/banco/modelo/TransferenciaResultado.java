package banco.modelo;

public record TransferenciaResultado(Transaccion enviada, Transaccion recibida) {

    @Override
    public String toString() {
        return """
            === Resultado de Transferencia ===
            → Transacción enviada: %s
            → Transacción recibida: %s
            """.formatted(enviada, recibida);
    }
}
