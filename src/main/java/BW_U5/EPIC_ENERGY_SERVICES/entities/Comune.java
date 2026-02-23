package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@ToString
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(nullable = false)
    private String nomeComune;
    @ManyToOne
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    public Comune(){}

    public Comune(String nomeComune, Provincia provincia) {
        this.nomeComune = nomeComune;
        this.provincia = provincia;
    }


}
