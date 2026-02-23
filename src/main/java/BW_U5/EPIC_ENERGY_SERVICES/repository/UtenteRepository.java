package BW_U5.EPIC_ENERGY_SERVICES.repository;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // Necessario per caricare l'utente durante il login
    Optional<Utente> findByUsername(String username);
    // gestire la ricerca dell' utente
    Optional<Utente> findByEmail(String email);
}