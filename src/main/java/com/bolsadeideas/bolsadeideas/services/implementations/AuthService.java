package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.UserRequestDTO;
import com.bolsadeideas.bolsadeideas.models.dao.IUsuarioDao;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import com.bolsadeideas.bolsadeideas.services.interfaces.IAuthService;
import com.bolsadeideas.bolsadeideas.services.interfaces.IJwtService;
import com.bolsadeideas.bolsadeideas.services.interfaces.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthService implements IAuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IJwtService jwtService;

    @Override
    public ResponseEntity login(UserRequestDTO usuario) {
        Map<String,Object> response= new HashMap<>();
        HttpStatus status;
        Usuario usuarioVerificado;

        try {
            log.info("username: " + usuario.getUsername() + "\n"
                    + "password: " + usuario.getPassword());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(), usuario.getPassword()
            );

            log.info("Antes de autenticar");
            log.info("Token: " + authToken.isAuthenticated());
            log.info("Authorities: " + authToken.getAuthorities());
            authenticationManager.authenticate(authToken);
            log.info("Despues de autenticar");
            usuarioVerificado = usuarioService.findUserByUsername(usuario.getUsername());

            String jwt = jwtService.generateToken(usuarioVerificado, generateExtraClaims(usuarioVerificado));

            response.put("jwt", jwt);
            status= HttpStatus.ACCEPTED;

        }catch(BadCredentialsException e){
            status= HttpStatus.BAD_REQUEST;
            response.put("error",e.getMessage());
        }
        return new ResponseEntity<Map<String,Object>>(response, status);
    }


    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims= new HashMap<>();

        extraClaims.put("id", usuario.getId());
        extraClaims.put("nombre", usuario.getNombre());
        extraClaims.put("apellido",usuario.getApellido());
        extraClaims.put("username", usuario.getUsername());
        extraClaims.put("email",usuario.getEmail());
        extraClaims.put("authorities",usuario.getAuthorities());

        return extraClaims;
    }


}
