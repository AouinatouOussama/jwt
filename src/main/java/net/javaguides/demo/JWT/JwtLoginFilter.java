package net.javaguides.demo.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.javaguides.demo.ClientModels.ClientModelRequest.Login;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            //pour mettre les info qui arrive depuis l'objet json (Credentials) dans l'objet de la class
            Login login = new ObjectMapper()
                    .readValue(request.getInputStream(), Login.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    login.getEmailOrUserName(),
                    login.getPassword()
            );

            System.out.println(login.toString());
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        Claims claims = Jwts.claims().setSubject(authResult.getName());
        claims.put("Authorities", authResult.getAuthorities());
        claims.put("IsAuthenticated", authResult.isAuthenticated());
        claims.put("Details", authResult.getDetails());
        claims.put("Principals", authResult.getPrincipal());

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .signWith(SignatureAlgorithm.HS256,"MySecretKey")
                .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }
}
