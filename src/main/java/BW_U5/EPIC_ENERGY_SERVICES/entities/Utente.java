package BW_U5.EPIC_ENERGY_SERVICES.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"password","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled"})
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ruoli_utenti",
    joinColumns = @JoinColumn(name = "id_utente", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "id_ruolo", nullable = false))
    private List<Ruolo> ruoli;

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
        return this.ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getRuolo())).toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}