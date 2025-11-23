package banco.modelo;

import java.util.List;
import java.util.Map;

public record ReporteGeneral(
        int totalClientes,
        int totalCuentas,
        double saldoTotal,
        double promedioSaldo,
        Cuenta cuentaMayorSaldo,
        Cuenta cuentaMenorSaldo,
        int totalTransacciones,
        List<Cuenta> top5Cuentas,
        Map<String, Long> cuentasPorTipo
) {}
