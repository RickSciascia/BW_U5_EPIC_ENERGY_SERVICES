package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Stato_fatture")
@Getter
@Setter
@NoArgsConstructor
public class Stato_fattura {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String stato;

    public Stato_fattura(String stato) {
        this.stato = stato;
    }
}
