package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValidationException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.UtenteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder; // Assicurati di avere questa dipendenza
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;


    @Autowired
    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder, EmailSender emailSender) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;

    }

    //POST

    public Utente saveUtente(UtenteDTO utenteDTO) {
        // 1 Controllo unicita email
        this.utenteRepository.findByEmail(utenteDTO.email()).ifPresent(utente -> {
            throw new ValidationException("L'email " + utenteDTO.email() + " è già in uso.");
        });

        // 2 Controllo unicita username
        this.utenteRepository.findByUsername(utenteDTO.username()).ifPresent(utente -> {
            throw new ValidationException("Lo username " + utenteDTO.username() + " è già in uso.");
        });

        // 3 Mapping DTO,entity con codifica password
        Utente utente = new Utente();
        utente.setUsername(utenteDTO.username());
        utente.setEmail(utenteDTO.email());
        utente.setNome(utenteDTO.nome());
        utente.setCognome(utenteDTO.cognome());

        // per la password
        utente.setPassword(passwordEncoder.encode(utenteDTO.password()));


        // 4 salvataggio nel db

        Utente savedUtente = utenteRepository.save(utente);

        // 5 invio dell email di benvenuto


        try {
            emailSender.sendWelcomeEmail(savedUtente);
        } catch (Exception e) {
            System.out.println("Errore invio email: " + e.getMessage());
        }

        return savedUtente;






    }

    //Get

    public Page<Utente> findAllUtenti(int size, int page, String sortBy) {
        if (page < 0) page = 0;
        if (size < 0 || size > 50) size = 10;
        if (sortBy == null) sortBy = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findById(long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato"));
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente con username " + username + " non trovato"));
    }

    //per eliminare

    public void findByIdAndDelete(long id) {
        Utente found = this.findById(id);
        this.utenteRepository.delete(found);
    }
}
