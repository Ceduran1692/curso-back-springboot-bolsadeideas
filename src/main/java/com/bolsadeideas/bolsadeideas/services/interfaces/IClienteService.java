package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IClienteService{
    ResponseEntity findAll();

    ResponseEntity findAll(int page);

    ResponseEntity save(Cliente cliente, BindingResult result);

    ResponseEntity findById(Long id);

    ResponseEntity delete(Long id);

    ResponseEntity updateCliente(Cliente cliente, Long id, BindingResult result);

    ResponseEntity upload(MultipartFile archivo, Long id);

    ResponseEntity verFoto(String nombreFoto);

    ResponseEntity getAllRegions();
}
