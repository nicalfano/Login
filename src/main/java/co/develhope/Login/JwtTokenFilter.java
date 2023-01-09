package co.develhope.Login;

import co.develhope.Login.auth.servicies.LoginService;
import co.develhope.Login.user.entities.User;
import co.develhope.Login.user.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;

    private Collection<? extends GrantedAuthority> getAuthorities(User user){
        if (user == null || !user.isActvive()) return List.of();
        return Arrays.asList(user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toArray(SimpleGrantedAuthority[]::new));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
        }

            final String token;
            try {
                token = header.split(" ")[1].trim();
            }catch (JWTVerificationException ex) {
                chain.doFilter(request, response);
                return;
            }
            DecodedJWT decoded = null;
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC512(LoginService.JWT_SECRET)).build();
                decoded = verifier.verify(token);
            } catch (JWTVerificationException ex) {
                chain.doFilter(request, response);
            }

            Optional<User> userDetails = userRepository.findById(decoded.getClaim("id").asLong());
            if (!userDetails.isPresent() || !userDetails.get().isActvive()) {
                chain.doFilter(request, response);

            }
            User user = userDetails.get();
            user.setPassword(null);
            user.setActvive(false);
            user.setPassword(null);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.get(), null, getAuthorities(user));
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

    }
}
