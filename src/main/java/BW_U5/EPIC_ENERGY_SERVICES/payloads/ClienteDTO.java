package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClienteDTO(
		@NotBlank
		String ragioneSociale,
		Long partitaIva,
		@Email(message = "Inserire un indirizzo email valido")
		@NotBlank
		String email,
		@NotNull
		@PastOrPresent
		LocalDate dataInserimento,
		LocalDate dataUltimoContatto,
		@NotNull
		@Positive
		Double fatturatoAnnuale,
		@Email(message = "Inserire una pec valida")
		@NotBlank
		String pec,
		Long numeroDiTelefono,
		@Email(message = "Inserire un indirizzo email valido")
		@NotBlank
		String emailDiContatto,
		@NotBlank
		String nomeContatto,
		@NotBlank
		String cognomeContatto,
		Long telefonoDiContatto,
		String logoAziendale,
		TipoCliente tipoCliente,
		IndirizzoDTO indirizzoLegale,
		IndirizzoDTO indirizzoCommerciale) {
}
