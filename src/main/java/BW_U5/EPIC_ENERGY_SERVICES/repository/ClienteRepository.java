package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


	// Ricerca per ID (già inclusa in JpaRepository, ma eccola per chiarezza)
	Optional<Cliente> findById(Long id);

	// Ricerca per Email aziendale
	Optional<Cliente> findByEmail(String email);

	// Ricerca per fatturato annuale
	List<Cliente> findByFatturatoAnnualeGreaterThan(double fatturatoAnnuale);

	// Ricerca per data inserimento
	List<Cliente> findByDataInserimentoBetween(LocalDate dataInserimento, LocalDate dataInserimento2);

	// Ricerca per ultimo contatto
	List<Cliente> findByDataUltimoContattoBetween(LocalDate inizio, LocalDate fine);

	// Ricerca per parte del nome
	List<Cliente> findByRagioneSocialeContainingIgnoreCase(String ragioneSociale);
}
