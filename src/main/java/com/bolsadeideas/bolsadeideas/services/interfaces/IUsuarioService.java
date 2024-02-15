package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Role;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUsuarioService {
    Usuario findUserByUsername(String username);
    List<GrantedAuthority>getAuthoritiesByUsername(String username);
}
