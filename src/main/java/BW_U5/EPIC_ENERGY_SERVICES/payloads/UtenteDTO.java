package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotBlank
        @Size(min = 3, max = 30)
        String username,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 6, max = 100)
        String password,

        @NotBlank
        String nome,

        @NotBlank
        String cognome,

        String avatar
) {
}