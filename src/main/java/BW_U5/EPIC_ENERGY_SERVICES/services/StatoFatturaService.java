package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.StatoFattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.StatoFatturaPayload;
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
    public StatoFattura salvaStatoFattura(StatoFatturaPayload payload){
        StatoFattura newStatoFattura = new StatoFattura(payload.getStato());
        StatoFattura statoFatturaSalvata = this.statoFatturaRepository.save(newStatoFattura);
        log.info("La fattura "+newStatoFattura.getStato()+ " è stata inserita con successo!");
        return statoFatturaSalvata;
    }

    //FIND ALL
    public List<StatoFattura> findAll() {
        return this.statoFatturaRepository.findAll();
    }

    //FIND BY ID
    public StatoFattura findById(long idStatoFattura) {
        return statoFatturaRepository.findById(idStatoFattura)
                .orElseThrow(() -> new NotFoundException("Lo stato fattura con id " + idStatoFattura + " non è stata trovata"));
    }

    //ELIMINA FATTURA
    public void findByIdAndDelete(long idStatoFattura) {
        StatoFattura found = this.findById(idStatoFattura);
        this.statoFatturaRepository.delete(found);
    }

}
