package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(@NotBlank(message = "il campo email è obbligatorio")
                       @Email(message = "Formato non corretto controlla la presenza di @ nella tua email!")
                       String email,
                       @NotBlank(message = "il campo password è obbligatorio")
                       @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
                       message = "La password deve contenere almeno una maiuscola, minuscola, numero ed essere minimo di 8 caratteri")
                       String password) {
}
