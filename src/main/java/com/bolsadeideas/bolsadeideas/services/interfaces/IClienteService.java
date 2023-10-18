package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface IClienteService{
    ResponseEntity findAll();

    ResponseEntity save(Cliente cliente, BindingResult result);

    ResponseEntity findById(Long id);

    ResponseEntity delete(Long id);

    ResponseEntity updateCliente(Cliente cliente, Long id, BindingResult result);
}
