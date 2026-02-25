package BW_U5.EPIC_ENERGY_SERVICES.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nome;
    private String cognome;
    private String avatar;

    @OneToMany(mappedBy = "utente")
    private List<RuoloUtente> ruoli;

    public Utente(String username, String email, String password, String nome, String cognome, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Ruolo> ruoloUtenteList = ruoli.stream().map(utente->utente.getRuolo()).toList();
        return List.of(new SimpleGrantedAuthority(ruoloUtenteList.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}