package BW_U5.EPIC_ENERGY_SERVICES.Security;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTSecret {
    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(Utente utente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis())) // Data di emissione (IaT - Issued At), va messa in millisecondi
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Data di scadenza (Expiration Date) anche questa va messa in millisecondi
                .subject(String.valueOf(utente.getId())) // Subject cioè a chi appartiene il token. Ci inseriamo l'id dell'utente (MAI METTERE DATI SENSIBILI AL SUO INTERNO)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firmo il token fornendogli un segreto che il server conosce ed usa per creare token ma anche per verificarli
                .compact();
    }

    public void verifyToken(String token) {

        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Effettua di nuovo il login!");
        }
}
    public long extractIdFromToken(String token){
        return Long.parseLong(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }
}