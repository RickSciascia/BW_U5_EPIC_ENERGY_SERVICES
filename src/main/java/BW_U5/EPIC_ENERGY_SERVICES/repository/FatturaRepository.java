package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    // Ricerca per ID primario
//    Optional<Fattura> findById(Long id);

    // Ricerca per Numero Fattura (univoco per il business)
    Optional<Fattura> findByNumero(int numero);

    // Esempio di logica: trovare l'ultima fattura di un cliente (opzionale)
    Optional<Fattura> findFirstByClienteOrderByDataDesc(Cliente cliente);

    List<Fattura> findByClienteId(long idCliente);

    List<Fattura> findByStatoFattura_Id(long idStatoFattura);

    List<Fattura> findByData(LocalDate data);

    List<Fattura> findByDataBetween(LocalDate inizio, LocalDate fine);

    List<Fattura> findByImportoBetween(double importoMin, double importoMax);

}
