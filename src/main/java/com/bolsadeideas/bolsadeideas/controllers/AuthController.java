package com.bolsadeideas.bolsadeideas.controllers;

import com.bolsadeideas.bolsadeideas.models.UserRequestDTO;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import com.bolsadeideas.bolsadeideas.services.implementations.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("")
    ResponseEntity authenticate(@RequestBody @Valid UserRequestDTO usuario){
        return authService.login(usuario);
    }
}
