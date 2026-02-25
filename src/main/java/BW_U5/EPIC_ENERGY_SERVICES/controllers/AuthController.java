package BW_U5.EPIC_ENERGY_SERVICES.controllers;


import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValException;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.ValidationException;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.LoginDTO;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.ResponseDTO;
import BW_U5.EPIC_ENERGY_SERVICES.payloads.UtenteDTO;
import BW_U5.EPIC_ENERGY_SERVICES.services.AuthService;
import BW_U5.EPIC_ENERGY_SERVICES.services.UtenteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UtenteService utenteService;

    public AuthController(AuthService authService, UtenteService utenteService) {
        this.authService = authService;
        this.utenteService = utenteService;

    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginDTO body) {

        return new ResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createUser(@RequestBody @Validated UtenteDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValException(errorsList);
        } else {
            return this.utenteService.saveUtente(payload);
        }

    }
    }
