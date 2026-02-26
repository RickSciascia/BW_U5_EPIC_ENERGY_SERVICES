package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Cliente;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Fattura;
import BW_U5.EPIC_ENERGY_SERVICES.entities.StatoFattura;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.FatturaPayload;
import BW_U5.EPIC_ENERGY_SERVICES.repository.ClienteRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.FatturaRepository;
import BW_U5.EPIC_ENERGY_SERVICES.repository.StatoFatturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static BW_U5.EPIC_ENERGY_SERVICES.specifications.FatturaSpecification.*;


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
        StatoFattura statoFattura = statoFatturaRepository.findById(payload.getStato_fattura())
                .orElseThrow(() -> new NotFoundException("Stato della fattura non trovato"));
        Fattura newFattura = new Fattura(payload.getData(), payload.getImporto(), payload.getNumero(),
                statoFattura, cliente);
        Fattura fatturaSalvata = this.fatturaRepository.save(newFattura);
        log.info("La fattura di "+newFattura.getCliente()+" con importo di " +newFattura.getImporto()+ " è stato inserita con successo!");
        return fatturaSalvata;
    }

    //FIND ALL
    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (page <= 0) page = 0;
        if (size < 0 || size > 150) size = 10;
        if (sortBy == null) sortBy = "name";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return fatturaRepository.findAll(pageable);
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
        StatoFattura statoFattura = statoFatturaRepository.findById(payload.getStato_fattura())
                .orElseThrow(() -> new NotFoundException("Stato della fattura non trovato"));

        Fattura found = this.findById(idFattura);
        found.setData(payload.getData());
        found.setImporto(payload.getImporto());
        found.setNumero(payload.getNumero());
        found.setStatoFattura(statoFattura);
        found.setCliente(cliente);

        Fattura fatturaModificata = this.fatturaRepository.save(found);
        log.info("La fattura con id " + fatturaModificata.getId() + " è stata modificata correttamente");
        return fatturaModificata;
    }


    public List<Fattura> filtriFattura(
            Integer numeroFattura,
            Long idCliente,
            Long idStato,
            LocalDate data,
            LocalDate inizio, LocalDate fine,
            Double importoMin, Double importoMax
    ){
        Specification<Fattura> spec = Specification.where((root, query, criteriaBuilder) -> null);

        if (numeroFattura != null){
            spec = spec.and(hasNumeroFattura(numeroFattura));
        }
        if(idCliente != null){
            spec = spec.and(hasIdCliente(idCliente));
        }
        if(idStato != null){
            spec = spec.and(hasIdStatoFattura(idStato));
        }
        if (data != null){
            spec = spec.and(hasData(data));
        }
        if (inizio != null && fine != null){
           spec = spec.and(hasDataBetween(inizio, fine));
        }
        if(importoMin != null && importoMax != null){
            spec = spec.and(hasImportoBetween(importoMin, importoMax));
        }
    return fatturaRepository.findAll(spec);
    }



}


