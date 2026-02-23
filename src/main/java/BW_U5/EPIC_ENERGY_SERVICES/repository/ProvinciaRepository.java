package BW_U5.EPIC_ENERGY_SERVICES.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    // per collegare i comuni alle province tramite sigla durante l'import
    Optional<Provincia> findBySigla(String sigla);
}
