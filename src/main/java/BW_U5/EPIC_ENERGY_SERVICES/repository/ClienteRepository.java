package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Ricerca per ID (già inclusa in JpaRepository, ma eccola per chiarezza)
    Optional<Cliente> findById(Long id);

    // Ricerca per Email aziendale
    Optional<Cliente> findByEmail(String email);

    // Ricerca per Partita IVA
    Optional<Cliente> findByPartitaIva(String partitaIva);

    // Ricerca per Ragione Sociale esatta
    Optional<Cliente> findByRagioneSociale(String ragioneSociale);
}
