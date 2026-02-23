package BW_U5.EPIC_ENERGY_SERVICES.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    // Filtro per Cliente
    Page<Fattura> findByClienteId(Long clienteId, Pageable pageable);

    // Filtro per Stato
    Page<Fattura> findByStatoNomeIgnoreCase(String statoNome, Pageable pageable);

    // Filtro per Data specifica
    Page<Fattura> findByData(LocalDate data, Pageable pageable);

    // Filtro per Anno (Query Custom)
    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    Page<Fattura> findByAnno(@Param("anno") int anno, Pageable pageable);

    // Filtro per Range di importi
    Page<Fattura> findByImportoBetween(BigDecimal min, BigDecimal max, Pageable pageable);
}
