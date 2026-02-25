package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteDTO(
		@NotBlank
		String ragioneSociale,
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
		double fatturatoAnnuale,
		@Email(message = "Inserire una pec valida")
		@NotBlank
		String pec,
		long numeroDiTelefono,
		@Email(message = "Inserire un indirizzo email valido")
		@NotBlank
		String emailDiContatto,
		@NotBlank
		String nomeContatto,
		@NotBlank
		String cognomeContatto,
		long telefonoDiContatto,
		String logoAziendale,
		TipoCliente tipoCliente,
		IndirizzoDTO indirizzoLegale,
		IndirizzoDTO indirizzoCommerciale) {
}
