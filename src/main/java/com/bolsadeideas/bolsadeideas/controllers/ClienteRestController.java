package com.bolsadeideas.bolsadeideas.controllers;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity create(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable Long id){
        return clienteService.delete(id);
    }

    @PutMapping("/{id}")
    ResponseEntity update(@RequestBody Cliente cliente,@PathVariable Long id){
        return clienteService.updateCliente(cliente, id);
    }
}
