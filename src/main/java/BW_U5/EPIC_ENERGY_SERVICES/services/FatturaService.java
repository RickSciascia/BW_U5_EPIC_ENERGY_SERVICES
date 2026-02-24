package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Stato_fattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.FatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ClienteRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.FatturaRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.StatoFatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class FatturaService {

    private final FatturaRepository fatturaRepository;
    private final ClienteRepository clienteRepository;
    private final StatoFatturaRepository statoFatturaRepository;

    @Autowired
    public FatturaService(FatturaRepository fatturaRepository, ClienteRepository clienteRepository, StatoFatturaRepository statoFatturaRepository) {
        this.fatturaRepository = fatturaRepository;
        this.clienteRepository = clienteRepository;
        this.statoFatturaRepository = statoFatturaRepository;
    }


    //SALVATAGGIO DELLE FATTURE
    public Fattura salvaFattura(FatturaPayload payload){
        //find by id fattura
        Cliente cliente = clienteRepository.findById(payload.getCliente())
                        .orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        //find by id stato fattura
        Stato_fattura statoFattura = statoFatturaRepository.findById(payload.getStato_fattura())
                .orElseThrow(() -> new NotFoundException("Stato della fattura non trovato"));
        Fattura newFattura = new Fattura(payload.getData(), payload.getImporto(), payload.getNumero(),
                statoFattura, cliente);
        Fattura fatturaSalvata = this.fatturaRepository.save(newFattura);
        log.info("La fattura di "+newFattura.getCliente()+" con importo di " +newFattura.getImporto()+ " è stato inserita con successo!");
        return fatturaSalvata;
    }

    //FIND ALL
    public List<Fattura> findAll() {
        return this.fatturaRepository.findAll();
    }

    //FIND BY ID
    public Fattura findById(long idFattura) {
        return fatturaRepository.findById(idFattura)
                .orElseThrow(() -> new NotFoundException("La fattura con id " + idFattura + " non è stata trovata"));
    }

    //ELIMINA FATTURA
    public void findByIdAndDelete(long idFattura) {
        Fattura found = this.findById(idFattura);
        this.fatturaRepository.delete(found);
        log.info("La fattura con id "+found.getId()+" del cliente " +found.getCliente()+ " è stato eliminata con successo!");
    }

    //MODIFICA FATTURA
    public Fattura findByIdAndUpdate(long idFattura, FatturaPayload payload) {
        //find by id fattura
        Cliente cliente = clienteRepository.findById(payload.getCliente())
                .orElseThrow(() -> new NotFoundException("Cliente non trovato"));
        //find by id stato fattura
        Stato_fattura statoFattura = statoFatturaRepository.findById(payload.getStato_fattura())
                .orElseThrow(() -> new NotFoundException("Stato della fattura non trovato"));

        Fattura found = this.findById(idFattura);
        found.setData(payload.getData());
        found.setImporto(payload.getImporto());
        found.setNumero(payload.getNumero());
        found.setStato_fattura(statoFattura);
        found.setCliente(cliente);

        Fattura fatturaModificata = this.fatturaRepository.save(found);
        log.info("La fattura con id " + fatturaModificata.getId() + " è stata modificata correttamente");
        return fatturaModificata;
    }

    //FIND BY NUMERO FATTURA
    public Fattura findByNumero(int numeroFattura){
        return fatturaRepository.findByNumero(numeroFattura)
                .orElseThrow(() -> new NotFoundException("La fattura con il numero " + numeroFattura + " non è stata trovata"));
    }
}
