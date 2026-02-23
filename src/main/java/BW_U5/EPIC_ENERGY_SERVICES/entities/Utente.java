package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nome;
    private String cognome;
    private String avatar;

    public Utente(String username, String email, String password, String nome, String cognome, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = avatar;
    }
}