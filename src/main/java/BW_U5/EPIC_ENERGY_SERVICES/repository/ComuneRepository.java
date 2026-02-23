package BW_U5.EPIC_ENERGY_SERVICES.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    // Utile per l'importazione dei comuni dal CSV
    Optional<Comune> findByNomeComune(String nomeComune);
}
