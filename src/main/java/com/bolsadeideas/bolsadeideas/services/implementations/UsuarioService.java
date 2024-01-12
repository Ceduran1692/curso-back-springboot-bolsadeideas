package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.dao.IUsuarioDao;
import com.bolsadeideas.bolsadeideas.models.entity.Role;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import com.bolsadeideas.bolsadeideas.services.interfaces.IUsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario= this.findUserByUsername(username);

        if(usuario == null){
            String mensaje= "Error en el login: no existe el usuario "+ username +"en el sistema";
            log.info(mensaje);
            throw  new UsernameNotFoundException(mensaje);
        }



        return new User(usuario.getUsername(),usuario.getPassword(),usuario.isEnabled(),usuario.isAccountNonExpired(),usuario.isCredentialsNonExpired(),usuario.isAccountNonLocked(),this.getAuthoritiesByUsername(username));
    }


    @Override
    @Transactional(readOnly = true)
    public Usuario findUserByUsername(String username) {
        return usuarioDao.findByUsername(username).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrantedAuthority> getAuthoritiesByUsername(String username) {
        log.info("getAuthoritiesByUsername - IN");
        List<Role> roles= this.usuarioDao.findRolesByUsername(username);

        log.info("roles: "+ roles.size());
        List<GrantedAuthority> authorities= roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());


        log.info("getAuthoritiesByUsername - OUT");
        return authorities;
    }


}
