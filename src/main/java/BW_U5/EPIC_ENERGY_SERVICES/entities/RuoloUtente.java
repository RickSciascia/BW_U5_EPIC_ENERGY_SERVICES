package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruolo", nullable = false)
    private Ruolo ruolo;

    public RuoloUtente(Utente utente, Ruolo ruolo) {
        this.utente = utente;
        this.ruolo = ruolo;
    }
}