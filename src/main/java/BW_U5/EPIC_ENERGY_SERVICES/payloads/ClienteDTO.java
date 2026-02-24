package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteDTO(
		@NotBlank
		String ragioneSociale,
		@Pattern(regexp = "^[0-9]{13}$", message = "La p.iva deve essere composta da 13 numeri")
		@NotBlank
		long partitaIva,
		@Email(message = "Inserire un indirizzo email valido")
		@NotBlank
		String email,
		@NotNull
		@PastOrPresent
		LocalDate dataInserimento,
		@PastOrPresent
		LocalDate dataUltimoContatto,
		@NotNull
		@Positive
		long fatturatoAnnuale,
		@Email(message = "Inserire una pec valida")
		@NotBlank
		String pec,
		@Pattern(regexp = "^[0-9]{10}$", message = "Il numero deve essere composta da almeno 10 numeri")
		long numeroDiTelefono,
		@Email(message = "Inserire un indirizzo email valido")
		@NotBlank
		String emailDiContatto,
		@NotBlank
		String nomeContatto,
		@NotBlank
		String cognomeContatto,
		@Pattern(regexp = "^[0-9]{10}$", message = "Il numero deve essere composta da almeno 10 numeri")
		long telefonoDiContatto,
		String logoAziendale,
		TipoCliente tipoCliente,
		IndirizzoDTO indirizzoLegale,
		IndirizzoDTO indirizzoCommerciale) {
}
