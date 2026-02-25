package BW_U5.EPIC_ENERGY_SERVICES.Security;

import BW_U5.EPIC_ENERGY_SERVICES.entities.Utente;
import BW_U5.EPIC_ENERGY_SERVICES.exceptions.UnauthorizedException;
import BW_U5.EPIC_ENERGY_SERVICES.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckedFilter extends OncePerRequestFilter {
    private final JWTSecret jwtSecret;
    private final UtenteService utenteService;

    public JWTCheckedFilter(JWTSecret jwtSecret, UtenteService utenteService) {
        this.jwtSecret = jwtSecret;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        ------------------------ AUTENTICAZIONE ------------------------

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization header nel formato corretto");
        String accessToken = authHeader.replace("Bearer ", "");
        jwtSecret.verifyToken(accessToken);

//        ------------------------ AUTORIZZAZIONE ------------------------

        long userId = jwtSecret.extractIdFromToken(accessToken);
        Utente utenteLoggato = this.utenteService.findById(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(utenteLoggato, null, utenteLoggato.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);


    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
