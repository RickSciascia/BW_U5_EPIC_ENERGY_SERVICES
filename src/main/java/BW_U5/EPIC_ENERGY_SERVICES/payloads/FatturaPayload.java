package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FatturaPayload {
    @FutureOrPresent
    private LocalDate data;
    private double importo;
    private int numero;
    private long stato_fattura;
    private long cliente;

    public FatturaPayload(LocalDate data, double importo, int numero, long stato_fattura, long cliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.stato_fattura = stato_fattura;
        this.cliente = cliente;
    }
}
