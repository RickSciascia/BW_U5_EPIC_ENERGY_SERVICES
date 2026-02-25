package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.NotBlank;

public record RuoloDTO(
        @NotBlank(message = "Il ruolo è un campo obbligatorio!")
        String ruolo
) {
}