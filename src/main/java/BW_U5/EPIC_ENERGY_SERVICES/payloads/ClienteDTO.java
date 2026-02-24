package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Indirizzo;
import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteDTO(
		@NotBlank
		String ragioneSociale,
		@Size(min = 11, max = 11)
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
		@NotNull
		long numeroDiTelefono,
		@Email
		@NotBlank
		String emailDiContatto,
		@NotBlank
		String nomeContatto,
		@NotBlank
		String cognomeContatto,
		@NotNull
		long telefonoDiContatto,
		String logoAziendale,
		@NotNull
		TipoCliente tipoCliente,
		Indirizzo indirizzoLegale,
		Indirizzo indirizzoCommerciale) {
}
