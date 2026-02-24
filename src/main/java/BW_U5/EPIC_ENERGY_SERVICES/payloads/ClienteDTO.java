package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClienteDTO(
		@NotBlank
		String ragioneSociale,
		long partitaIva,
		@Email
		@NotBlank
		String email,
		LocalDate dataInserimento,
		LocalDate dataUltimoContatto,
		long fatturatoAnnuale,
		@Email
		@NotBlank
		String pec,
		long numeroDiTelefono,
		@Email
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
