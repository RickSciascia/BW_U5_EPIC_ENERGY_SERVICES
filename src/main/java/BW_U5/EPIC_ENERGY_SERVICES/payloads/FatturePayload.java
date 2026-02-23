package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Stato_fattura;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FatturePayload {
    private LocalDate data;
    private int importo;
    private int numero;
    private long stato_fattura;
    private long cliente;

    public FatturePayload(LocalDate data, int importo, int numero, long stato_fattura, long cliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.stato_fattura = stato_fattura;
        this.cliente = cliente;
    }
}
