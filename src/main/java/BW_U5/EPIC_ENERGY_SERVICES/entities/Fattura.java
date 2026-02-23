package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
public class Fattura {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private LocalDate data;
    private double importo;
    private int numero;

  @ManyToOne
  @JoinColumn(name = "id_stato")
    private Stato_fattura stato_fattura;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

    public Fattura(LocalDate data, int importo, int numero, Stato_fattura stato_fattura, Cliente cliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.stato_fattura = stato_fattura;
        this.cliente = cliente;
    }
}
