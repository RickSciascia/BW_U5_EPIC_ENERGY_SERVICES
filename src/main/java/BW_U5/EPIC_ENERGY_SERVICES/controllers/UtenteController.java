package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.UtenteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    // POST - Registrazione
    //POST http://localhost:3001/utenti
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody UtenteDTO body) {
        return utenteService.saveUtente(body);
    }

    // GET ALL - Lista utenti
    // GET http://localhost:3001/utenti?page=0&size=10&sortBy=username
    @GetMapping
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
    public Utente getUtenteById(@PathVariable long id) {
        return utenteService.findById(id);
    }

    // Elimina un utente
    // DELETE http://localhost:3001/utenti/5
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtente(@PathVariable long id) {
        utenteService.findByIdAndDelete(id);
    }
}
