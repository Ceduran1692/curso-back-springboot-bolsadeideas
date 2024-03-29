package com.bolsadeideas.bolsadeideas.controllers;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins= {"http://localhost:4200"},
        allowedHeaders = {"Authorization","Content-type"},
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.OPTIONS}
        )
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("")
    ResponseEntity findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/page/{page}")
    ResponseEntity findAll(@PathVariable int page){
        return clienteService.findAll(page);
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

    @PostMapping("/upload")
    ResponseEntity upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
        return clienteService.upload(archivo,id);
    }
    @GetMapping("/upload/img/{nombreFoto:.+}")
    ResponseEntity<Resource> verfoto(@PathVariable("nombreFoto") String nombreFoto){
        return clienteService.verFoto(nombreFoto);
    }

    @GetMapping("/regiones")
    ResponseEntity getAllRegiones(){
        return clienteService.getAllRegions();
    }
}
