package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {


	// Ricerca per ID (già inclusa in JpaRepository, ma eccola per chiarezza)
	Optional<Cliente> findById(Long id);

	// Ricerca per Email aziendale
	Optional<Cliente> findByEmail(String email);

	Page<Cliente> findAll(Pageable pageable);

}
