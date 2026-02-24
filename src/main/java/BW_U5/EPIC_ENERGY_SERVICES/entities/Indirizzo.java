package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@ToString
public class Indirizzo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private long id;
	@Column(nullable = false)
	private String via;
	@Column(nullable = false)
	private String civico;
	@Column(nullable = false)
	private String cap;
	@ManyToOne
	@JoinColumn(name = "id_comune", nullable = false)
	private Comune comune;

	public Indirizzo() {
	}

	public Indirizzo(String via, String civico, String cap, Comune comune) {
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.comune = comune;
	}

}
