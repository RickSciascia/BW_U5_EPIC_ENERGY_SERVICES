package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Ruolo;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.RuoloDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.RuoloService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {
    private final RuoloService ruoloService;

    public RuoloController(RuoloService ruoloService) {
        this.ruoloService = ruoloService;
    }

    //    POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo salvaRuolo(@RequestBody @Validated RuoloDTO payload, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            List<String> listaErrori = validationResult.getFieldErrors()
                    .stream().map(fe->fe.getDefaultMessage())
                    .toList();
            throw new ValException(listaErrori);
        } else {
            return this.ruoloService.saveNuovoRuolo(payload);
        }
    }
//    GET
    @GetMapping
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public List<Ruolo>getAllRuoli() {
        return this.ruoloService.findAllRuoli();
    }
//    GET specifica
    @GetMapping("/{idRuolo}")
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public Ruolo getRuoloById(@PathVariable long idRuolo) {
        return this.ruoloService.findRuoloById(idRuolo);
    }

//    PUT
    @PutMapping("/{idRuolo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ruolo editRuolo(@RequestBody @Validated RuoloDTO payload, BindingResult valResult, @PathVariable long idRuolo) {
        if(valResult.hasErrors()) {
            List<String> listaErrori = valResult.getFieldErrors()
                    .stream().map(fe->fe.getDefaultMessage())
                    .toList();
            throw new ValException(listaErrori);
        } else {
            return this.ruoloService.editRuolo(payload,idRuolo);
        }
    }
//    DELETE
    @DeleteMapping("/{idRuolo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRuolo(@PathVariable long idRuolo) {
        this.ruoloService.findRuoloByIdAndDelete(idRuolo);
    }


}
