package BW_U5.EPIC_ENERGY_SERVICES.controllers;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.FatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.services.ClienteService;
import BW_U5.EPIC_ENERGY_SERVICES.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Fattura salvaFattura(@RequestBody FatturaPayload payload) {
        return this.fatturaService.salvaFattura(payload);
    }

    //DELETE
    @DeleteMapping("/{idFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long idFattura) {
        this.fatturaService.findByIdAndDelete(idFattura);
    }


    //GET
    @GetMapping("/id/{idFattura}")
    public Fattura findById(@PathVariable long idFattura) {
        return this.fatturaService.findById(idFattura);
    }

    //GET
    @GetMapping("/numero/{numero}")
    public Fattura findByNumero(@PathVariable int numero) {
        return this.fatturaService.findByNumero(numero);
    }

    //GET
    @GetMapping
    public List<Fattura> findAll(){
        return this.fatturaService.findAll();
    }

    //PUT
    @PutMapping("/{idFattura}")
    public Fattura findByIdAndUpdate(@PathVariable long idFattura, @RequestBody FatturaPayload payload) {
        return this.fatturaService.findByIdAndUpdate(idFattura, payload);
    }

    //RICERCA PER ID CLIENTE
    @GetMapping("/cliente/{idCliente}")
    public List<Fattura> findByClienteId(@PathVariable long idCliente){
        return this.fatturaService.findByClienteId(idCliente);
    }

    //RICERCA PER STATO FATTURA
    @GetMapping("/stato/{idStatoFattura}")
    public List<Fattura> findByStatoFattura_Id(@PathVariable long idStatoFattura){
        return this.fatturaService.findByStatoFattura_Id(idStatoFattura);
    }

    //RICERCA PER DATA
    @GetMapping("data/{data}")
    public List<Fattura> findByData(@PathVariable LocalDate data ){
        return this.fatturaService.findByData(data);
    }

    //RICERCA PER ANNO
    @GetMapping("anno/{inizio}/{fine}")
    public List<Fattura> findByDataBetween(@PathVariable LocalDate inizio,@PathVariable LocalDate fine ){
        return this.fatturaService.findByDataBetween(inizio, fine);
    }

    //RICERCA PER RANGE DI IMPORTO
    @GetMapping("importo/{importoMin}/{importoMax}")
    public List<Fattura> findByImportoBetween(@PathVariable double importoMin, @PathVariable double importoMax ){
        return this.fatturaService.findByImportoBetween(importoMin, importoMax);
    }
}
