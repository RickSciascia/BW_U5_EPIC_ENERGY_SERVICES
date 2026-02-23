package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long> {
}
