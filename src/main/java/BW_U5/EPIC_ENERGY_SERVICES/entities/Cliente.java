package BW_U5.EPIC_ENERGY_SERVICES.entities;

import BW_U5.EPIC_ENERGY_SERVICES.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private long id;
	@Column(nullable = false)
	private String ragioneSociale;
	@Column(nullable = false, unique = true)
	@Setter(AccessLevel.NONE)
	private long partitaIva;
	@Column(nullable = false, unique = true)
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	@Setter(AccessLevel.NONE)
	private long fatturatoAnnuale;
	@Column(nullable = false, unique = true)
	@Setter(AccessLevel.NONE)
	private String pec;
	@Column(nullable = false)
	private long numeroDiTelefono;
	@Column(nullable = false, unique = true)
	private String emailDiContatto;
	@Column(nullable = false)
	private String nomeContatto;
	@Column(nullable = false)
	private String cognomeContatto;
	@Column(nullable = false)
	private long telefonoDiContatto;
	private String logoAziendale;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCliente tipoCliente;
	@OneToOne
	private Indirizzo indirizzoLegale;
	@OneToOne
	private Indirizzo indirizzoCommerciale;

	public Cliente(
			String ragioneSociale,
			long partitaIva,
			String email,
			LocalDate dataInserimento,
			LocalDate dataUltimoContatto,
			long fatturatoAnnuale,
			String pec,
			long numeroDiTelefono,
			String emailDiContatto,
			String nomeContatto,
			String cognomeContatto,
			long telefonoDiContatto,
			String logoAziendale,
			TipoCliente tipoCliente,
			Indirizzo indirizzoLegale,
			Indirizzo indirizzoCommerciale
	) {
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
		this.pec = pec;
		this.numeroDiTelefono = numeroDiTelefono;
		this.emailDiContatto = emailDiContatto;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoDiContatto = telefonoDiContatto;
		this.logoAziendale = logoAziendale;
		this.tipoCliente = tipoCliente;
		this.indirizzoLegale = indirizzoLegale;
		this.indirizzoCommerciale = indirizzoCommerciale;
	}

}
