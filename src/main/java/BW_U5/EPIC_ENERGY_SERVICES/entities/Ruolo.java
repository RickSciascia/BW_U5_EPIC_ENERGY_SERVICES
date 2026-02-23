package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ruolo {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String ruolo;

    public Ruolo(String ruolo) {
        this.ruolo = ruolo;
    }
}