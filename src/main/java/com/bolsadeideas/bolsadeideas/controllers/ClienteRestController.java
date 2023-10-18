package com.bolsadeideas.bolsadeideas.controllers;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins= {"http://localhost:4200"})
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("")
    ResponseEntity findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PostMapping("")
    ResponseEntity create(@Valid @RequestBody Cliente cliente, BindingResult result){
        return clienteService.save(cliente, result);
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id){
        return clienteService.delete(id);
    }

    @PutMapping("/{id}")
    ResponseEntity update(@Valid @RequestBody Cliente cliente,@PathVariable Long id, BindingResult result){
        return clienteService.updateCliente(cliente, id, result);
    }
}
