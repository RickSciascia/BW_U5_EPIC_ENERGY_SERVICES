package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private final String apiKey;
    private final String domain;
    private final String from;
    private final String baseUrl;

    public EmailSender(
            @Value("${mg.apikey}") String apiKey,
            @Value("${mg.domain}") String domain,
            @Value("${mg.from}") String from,
            @Value("${mg.base-url}") String baseUrl
    ) {
        this.apiKey = apiKey;
        this.domain = domain;
        this.from = from;
        this.baseUrl = baseUrl;
    }

    public JsonNode sendWelcomeEmail(Utente utente) throws UnirestException {
        String nomeCompleto = utente.getUsername();

        if (utente.getNome() != null && !utente.getNome().isBlank()) {
            nomeCompleto = utente.getNome();

            if (utente.getCognome() != null && !utente.getCognome().isBlank()) {
                nomeCompleto += " " + utente.getCognome();
            }
        }

        String url = baseUrl + "/v3/" + domain + "/messages";

        HttpResponse<JsonNode> request = Unirest.post(url)
                .basicAuth("api", apiKey)
                .field("from", "Epic Energy Services <" + from + ">")
                .field("to", utente.getEmail())
                .field("subject", "Benvenuto " + nomeCompleto)
                .field("text", "Ciao " + nomeCompleto + ", il tuo account è stato creato con successo!")
                .asJson();

        if (request.getStatus() >= 400) {
            throw new RuntimeException("Errore Mailgun: " + request.getBody());
        }

        return request.getBody();
    }
}