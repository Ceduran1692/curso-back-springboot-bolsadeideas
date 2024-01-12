package com.bolsadeideas.bolsadeideas.config.auth.filters;

import com.bolsadeideas.bolsadeideas.models.dao.IUsuarioDao;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import com.bolsadeideas.bolsadeideas.services.interfaces.IJwtService;
import com.bolsadeideas.bolsadeideas.services.interfaces.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    IJwtService jwtService;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("JwtAuthenticationFilter - doFilterInternal() - IN");
        //Extraigo el header
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null){
            authHeader= request.getHeader("authorization");
        }
        Enumeration<String> headers= request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String elemento = headers.nextElement();
            // Haz algo con el elemento, por ejemplo, imprimirlo
            log.info("header: "+ elemento);
        }

        log.info("Headers: " + request.getHeaderNames().toString());
        log.info("authHeader: " + authHeader);


        if (authHeader != null && authHeader.startsWith("Bearer")) {

            //Extraigo el jwt del header
            //String jwt = authHeader.split(" ")[1];
            String jwt = authHeader.substring(7).replace(" ","");
            log.info("jwt: " + jwt);
           /*
            if(jwt.contains("Bearer")){
                jwt = authHeader.split(",")[0];
                log.info("jwt: " + jwt);

            }
            */

            //Obtengo el subject del jwt
            String username = jwtService.extractUsername(jwt.trim());

            //Seteo el objeto Authentication dentro del securityContexHolder

            Usuario usuario = usuarioService.findUserByUsername(username);

            log.info("Usuario:  " + usuario.getUsername());

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(), null, usuarioService.getAuthoritiesByUsername(username)
            );

            log.info("Token: " + token.isAuthenticated());

            SecurityContextHolder.getContext().setAuthentication(token);
        }
        //Ejecutar lo otros filtros

        filterChain.doFilter(request, response);

        log.info("JwtAuthenticationFilter - doFilterInternal() - OUT");

    }
}
