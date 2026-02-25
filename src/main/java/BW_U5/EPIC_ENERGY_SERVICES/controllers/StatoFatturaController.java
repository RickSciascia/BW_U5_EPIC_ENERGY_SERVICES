package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.StatoFattura;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.StatoFatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statofattura")
public class StatoFatturaController {

    private StatoFatturaService statoFatturaService;

    @Autowired
    public StatoFatturaController(StatoFatturaService statoFatturaService) {
        this.statoFatturaService = statoFatturaService;
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFattura salvaFattura(@RequestBody StatoFatturaPayload payload) {
        return this.statoFatturaService.salvaStatoFattura(payload);
    }

    //DELETE
    @DeleteMapping("/{idStatoFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long idStatoFattura) {
        this.statoFatturaService.findByIdAndDelete(idStatoFattura);
    }

    //GET
    @GetMapping("/{idStatoFattura}")
    public StatoFattura findById(@PathVariable long idStatoFattura) {
        return this.statoFatturaService.findById(idStatoFattura);
    }

    //GET
    @GetMapping
    public List<StatoFattura> findAll(){
        return this.statoFatturaService.findAll();
    }
}
