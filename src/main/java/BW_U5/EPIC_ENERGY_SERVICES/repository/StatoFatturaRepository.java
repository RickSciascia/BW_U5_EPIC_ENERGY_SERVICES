package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Provincia;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Stato_fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoFatturaRepository extends JpaRepository<Stato_fattura, Long> {
}
