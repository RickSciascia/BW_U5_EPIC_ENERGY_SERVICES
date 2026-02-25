package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Ruolo;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.NotFoundException;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValidationException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.RuoloDTO;
import BW_U5.EPIC_ENERGY_SERVICES.repository.RuoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloService {

    private final RuoloRepository ruoloRepository;

    public RuoloService(RuoloRepository ruoloRepository) {
        this.ruoloRepository = ruoloRepository;
    }

//    SAVE
    public Ruolo saveNuovoRuolo(RuoloDTO payload) {
        this.ruoloRepository.findByRuolo(payload.ruolo()).ifPresent(ruolo ->
        {throw new ValidationException("Il ruolo: " + ruolo.getRuolo() + " è già presente in Database!");
        });
        Ruolo nuovoRuolo = new Ruolo(payload.ruolo());
        Ruolo ruoloSalvato = this.ruoloRepository.save(nuovoRuolo);
        System.out.println("Ruolo: " + ruoloSalvato.getRuolo() + " salvato correttamente!");
        return ruoloSalvato;
    }

    public Ruolo editRuolo(RuoloDTO payload, long idRuolo) {
        Ruolo ruoloDaAggiornare =  this.findRuoloById(idRuolo);
        ruoloDaAggiornare.setRuolo(payload.ruolo());
        Ruolo ruoloAggiornato = this.ruoloRepository.save(ruoloDaAggiornare);
        System.out.println("Ruolo modificato in: " + ruoloAggiornato.getRuolo());
        return ruoloAggiornato;
    }

//    GET

    public List<Ruolo> findAllRuoli() {
        return this.ruoloRepository.findAll();
    }
//    GET SPECIFICA

    public Ruolo findRuoloById(long idRuolo) {
        return this.ruoloRepository.findById(idRuolo).orElseThrow(()-> new NotFoundException("Ruolo non trovato"));
    }

//    DELETE
    public void findRuoloByIdAndDelete(long idRuolo) {
        Ruolo ruoloDaCancellare = this.findRuoloById(idRuolo);
        this.ruoloRepository.delete(ruoloDaCancellare);
    }

}
