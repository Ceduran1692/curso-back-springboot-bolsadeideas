package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Usuario;

import java.util.Map;

public interface IJwtService {

    String generateToken(Usuario usuario, Map<String,Object> extraClaims);
}
