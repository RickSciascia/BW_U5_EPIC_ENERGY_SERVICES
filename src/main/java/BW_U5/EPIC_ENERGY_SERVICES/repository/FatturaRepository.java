package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepository extends CrudRepository<Fattura, Long>, JpaSpecificationExecutor<Fattura> {

    // Ricerca per ID primario
//    Optional<Fattura> findById(Long id);

    Page<Fattura> findAll(Pageable pageable);

    // Esempio di logica: trovare l'ultima fattura di un cliente (opzionale)
    Optional<Fattura> findFirstByClienteOrderByDataDesc(Cliente cliente);



}
