package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import lombok.Getter;

@Getter
public class StatoFatturaPayload {
    private String stato;

    public StatoFatturaPayload(String stato) {
        this.stato = stato;
    }
}
