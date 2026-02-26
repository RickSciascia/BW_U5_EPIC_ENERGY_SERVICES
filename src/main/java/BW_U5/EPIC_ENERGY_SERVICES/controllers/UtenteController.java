package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.UtenteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // GET ALL - Lista utenti
    // GET http://localhost:3001/utenti?page=0&size=10&sortBy=username
    @GetMapping
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public Page<Utente> getAllUtenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "username") String sortBy
    ) {
        return utenteService.findAllUtenti(size, page, sortBy);
    }

    //  GET BY ID
    // GET http://localhost:3001/utenti/5
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public Utente getUtenteById(@PathVariable long id) {
        return utenteService.findById(id);
    }

    // Elimina un utente
    // DELETE http://localhost:3001/utenti/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUtente(@PathVariable long id) {
        utenteService.findByIdAndDelete(id);
    }
}
