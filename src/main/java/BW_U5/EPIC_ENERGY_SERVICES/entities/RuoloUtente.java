package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "ruoli_utenti",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_ruoli_utenti_utente_ruolo",
                columnNames = {"id_utente", "id_ruolo"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"utente", "ruolo"})
public class RuoloUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ruolo", nullable = false)
    private Ruolo ruolo;

    public RuoloUtente(Utente utente, Ruolo ruolo) {
        this.utente = utente;
        this.ruolo = ruolo;
    }
}