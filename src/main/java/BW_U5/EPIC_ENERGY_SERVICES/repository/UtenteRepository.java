package BW_U5.EPIC_ENERGY_SERVICES.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // Necessario per caricare l'utente durante il login
    Optional<Utente> findByUsername(String username);
    // gestire la ricerca dell' utente
    Optional<Utente> findByEmail(String email);
}