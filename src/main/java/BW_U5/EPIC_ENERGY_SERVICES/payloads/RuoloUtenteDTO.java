package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.NotNull;

public record RuoloUtenteDTO(
        @NotNull
        Long idUtente,

        @NotNull
        Long idRuolo
) {
}