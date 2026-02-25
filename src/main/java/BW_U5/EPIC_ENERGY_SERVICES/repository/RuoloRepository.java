package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Optional<Ruolo> findByRuolo(String ruolo);
}
