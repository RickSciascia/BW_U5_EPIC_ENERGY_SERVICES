package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Stato_fattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.FatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.StatoFatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.repository.FatturaRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.StatoFatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatoFatturaService {

    private final StatoFatturaRepository statoFatturaRepository;

    @Autowired

    public StatoFatturaService(StatoFatturaRepository statoFatturaRepository) {
        this.statoFatturaRepository = statoFatturaRepository;
    }

    //SALVATAGGIO DELLE FATTURE
    public Stato_fattura salvaStatoFattura(StatoFatturaPayload payload){
        Stato_fattura newStatoFattura = new Stato_fattura(payload.getStato());
        Stato_fattura statoFatturaSalvata = this.statoFatturaRepository.save(newStatoFattura);
        log.info("La fattura "+newStatoFattura.getStato()+ " è stata inserita con successo!");
        return statoFatturaSalvata;
    }

    //FIND ALL
    public List<Stato_fattura> findAll() {
        return this.statoFatturaRepository.findAll();
    }

    //FIND BY ID
    public Stato_fattura findById(long idStatoFattura) {
        return statoFatturaRepository.findById(idStatoFattura)
                .orElseThrow(() -> new NotFoundException("Lo stato fattura con id " + idStatoFattura + " non è stata trovata"));
    }

    //ELIMINA FATTURA
    public void findByIdAndDelete(long idStatoFattura) {
        Stato_fattura found = this.findById(idStatoFattura);
        this.statoFatturaRepository.delete(found);
    }

}
