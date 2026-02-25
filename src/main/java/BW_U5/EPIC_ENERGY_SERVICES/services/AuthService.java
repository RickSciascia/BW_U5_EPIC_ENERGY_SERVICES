package BW_U5.EPIC_ENERGY_SERVICES.services;

import BW_U5.EPIC_ENERGY_SERVICES.Security.JWTSecret;
import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.UnauthorizedException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtenteService usersService;
    private final JWTSecret jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UtenteService usersService, JWTSecret jwtSecret, PasswordEncoder bcrypt) {

        this.usersService = usersService;
        this.jwtTools = jwtSecret;
        this.bcrypt = bcrypt;
    }

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.generateToken(found);
            return accessToken;

        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }


    }
}
