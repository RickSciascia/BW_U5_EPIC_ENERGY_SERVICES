package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.FatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.services.ClienteService;
import BW_U5.EPIC_ENERGY_SERVICES.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

    private FatturaService fatturaService;

    @Autowired
    public FatturaController(FatturaService fatturaService) {
        this.fatturaService = fatturaService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura salvaFattura(@RequestBody FatturaPayload payload) {
        return this.fatturaService.salvaFattura(payload);
    }

    //DELETE
    @DeleteMapping("/{idFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable long idFattura) {
        this.fatturaService.findByIdAndDelete(idFattura);
    }


    //GET
    @GetMapping("/id/{idFattura}")
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public Fattura findById(@PathVariable long idFattura) {
        return this.fatturaService.findById(idFattura);
    }


    //GET
    @GetMapping
    @PreAuthorize("hasAnyAuthority('UTENTE','ADMIN')")
    public Page<Fattura> findAllClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "statoFattura") String orderBy) {
        return fatturaService.findAll(page, size, orderBy);
    }

    //PUT
    @PutMapping("/{idFattura}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByIdAndUpdate(@PathVariable long idFattura, @RequestBody FatturaPayload payload) {
        return this.fatturaService.findByIdAndUpdate(idFattura, payload);
    }

    @GetMapping("/filter")
    public List<Fattura> filtroFatture(
            @RequestParam(required = false) Integer numeroFattura,
            @RequestParam(required = false) Long idCliente,
            @RequestParam(required = false) Long idStato,
            @RequestParam(required = false) LocalDate data,
            @RequestParam(required = false) LocalDate inizio,
            @RequestParam(required = false) LocalDate fine,
            @RequestParam(required = false) Double importoMin,
            @RequestParam(required = false) Double importoMax
    ){
        return fatturaService.filtriFattura(
                 numeroFattura,
                 idCliente,
                 idStato,
                 data,
                 inizio,
                 fine,
                 importoMin,
                importoMax
        );
    }

}
