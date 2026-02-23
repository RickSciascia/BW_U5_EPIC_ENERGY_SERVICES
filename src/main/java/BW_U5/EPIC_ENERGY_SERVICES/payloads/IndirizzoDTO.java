package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record IndirizzoDTO(
        @NotBlank(message = "Il campo via è obbligatorio")
        String via,
        @NotBlank(message = "Il civico è un campo obbligatorio")
        String civico,
        @NotBlank(message = "Il CAP è un campo obbligatorio")
        @Size(min = 5, max = 5, message = "Il CAP deve essere necessariamente lungo 5 cifre!")
        @Pattern(regexp = "^[0-9]{5}$", message = "Il CAP deve contenere solamente numeri!")
        String cap,
        @NotNull(message = "L'id del Comune è necessario!")
        Long idComune
) {
}
