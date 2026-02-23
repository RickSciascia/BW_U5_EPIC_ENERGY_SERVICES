package BW_U5.EPIC_ENERGY_SERVICES.repository;

import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Filtro per parte del nome (Ragione Sociale)
    Page<Cliente> findByRagioneSocialeContainingIgnoreCase(String nome, Pageable pageable);

    // Filtro per fatturato annuale
    Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    // Filtro per data di inserimento
    Page<Cliente> findByDataInserimentoBetween(LocalDate start, LocalDate end, Pageable pageable);

    // Filtro per data ultimo contatto
    Page<Cliente> findByDataUltimoContattoBetween(LocalDate start, LocalDate end, Pageable pageable);

}
