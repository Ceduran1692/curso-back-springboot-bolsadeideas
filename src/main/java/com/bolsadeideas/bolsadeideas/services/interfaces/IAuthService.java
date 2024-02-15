package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.UserRequestDTO;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthService {

    ResponseEntity login(UserRequestDTO usuario);

}
