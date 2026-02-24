package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    // per collegare i comuni alle province tramite sigla durante l'import
    Optional<Provincia> findBySigla(String sigla);
    Optional<Provincia> findByProvincia(String provincia);
}
