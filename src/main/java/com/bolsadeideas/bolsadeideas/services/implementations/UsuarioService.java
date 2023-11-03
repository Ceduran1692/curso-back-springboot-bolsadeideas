package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.dao.IUsuarioDao;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario= usuarioDao.findByUsername(username).get();

        if(usuario == null){
            String mensaje= "Error en el login: no existe el usuario "+ username +"en el sistema";
            log.info(mensaje);
            throw  new UsernameNotFoundException(mensaje);
        }

        List<GrantedAuthority> authorities= usuario.getRoles().stream()
                                                                .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                                .peek(auth -> log.info("role "+ auth.getAuthority()))
                                                                .collect(Collectors.toList());

        return new User(usuario.getUsername(),usuario.getPassword(),usuario.isEnable(),true,true,true,authorities);
    }
}
