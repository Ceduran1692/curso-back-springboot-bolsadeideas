package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import com.bolsadeideas.bolsadeideas.services.interfaces.IJwtService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements IJwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration.minutes}")
    private long EXPIRATION_MINUTES;

    @Override
    public String generateToken(Usuario usuario, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)                  //Asigno las peticiones extras
                .subject(usuario.getUsername())       //Se envia el username
                .issuedAt(getIssuedAt())
                .expiration(getExpiration())
                .signWith(generateKey())
                .compact()
        ;
    }

    private Key generateKey() {
        byte[] byteKey= Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(byteKey);
    }

    private Date getIssuedAt(){
        return new Date(System.currentTimeMillis());
    }

    private Date getExpiration(){
        return new Date(getIssuedAt().getTime() + (EXPIRATION_MINUTES * 60 * 1000));
    }


}
