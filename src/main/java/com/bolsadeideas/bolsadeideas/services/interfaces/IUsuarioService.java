package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Usuario;

public interface IUsuarioService {
    Usuario findUserByUsername(String username);
}
