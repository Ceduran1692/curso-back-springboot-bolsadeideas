package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.dao.IClienteDao;
import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.models.entity.Region;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import com.bolsadeideas.bolsadeideas.services.interfaces.IUploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteDao clientedao;

    @Autowired
    private IUploadFileService fileService;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity findAll() {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
           List<Cliente> lista = (List<Cliente>) clientedao.findAll();
           log.info("cantidad de clientes: "+ lista.size());
           if (lista.size() > 0) {
                status= HttpStatus.OK;
                response.put("value",lista);
           }else{
               msg= "No existen clientes en la base de dato";
               status= HttpStatus.NOT_FOUND;
               response.put("mensaje",msg);
           }
       }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
       }

        return new ResponseEntity<Map<String, Object>>(response,status);

    }

    @Override
    public ResponseEntity findAll(int page) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
           Page<Cliente> lista =  clientedao.findAll(PageRequest.of(page,4));
            log.info("cantidad de clientes: "+ lista.getTotalPages());
            if (lista.toList().size() > 0) {
                status= HttpStatus.OK;
                response.put("value",lista);
            }else{
                log.info("entre al else");
                msg= "No existen clientes en la base de dato";
                status= HttpStatus.NOT_FOUND;
                response.put("mensaje",msg);
            }
        }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }

        return new ResponseEntity<Map<String, Object>>(response,status);
    }

    @Override
    @Transactional
    public ResponseEntity save(Cliente cliente, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        if(result.hasErrors()){
            List<String> errors= new ArrayList<>();

            for(FieldError err: result.getFieldErrors()) {
                errors.add("El campo "+ err.getField() +": "+err.getDefaultMessage());
            }
            status = HttpStatus.BAD_REQUEST;
            msg= "Error al realizar la validacion de datos";
            response.put("mensaje", msg);
            response.put("error", errors);
        }else {
            try {
                Cliente clienteNew = clientedao.save(cliente);
                status = HttpStatus.CREATED;
                response.put("value", clienteNew);

            } catch (Exception e) {
                msg = "Error al realizar el impacto en la base de datos, Error:";
                error = e.getMessage();
                status = HttpStatus.INTERNAL_SERVER_ERROR;

                response.put("mensaje", msg);
                response.put("error", error);
            }
        }
        return new ResponseEntity<Map<String, Object>>(response,status);

    }

    @Override
    public ResponseEntity findById(Long id) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            Cliente cliente = clientedao.findById(id).get();

            if (cliente != null) {
                status= HttpStatus.OK;
                response.put("value",cliente);
            }else{
                msg= "No existe el cliente con id: "+id+" en la base de dato";
                status= HttpStatus.NOT_FOUND;
                response.put("mensaje",msg);
            }
        }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }

        return new ResponseEntity<Map<String, Object>>(response,status);

    }

    @Override
    public ResponseEntity delete(Long id) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            Cliente cliente = clientedao.findById(id).get();

            if (cliente != null) {
                fileService.eliminar(cliente.getFoto());
                clientedao.delete(cliente);
                status= HttpStatus.OK;
                response.put("value",cliente);
            }else{
                msg= "No existe el cliente con id: "+id+" en la base de dato";
                status= HttpStatus.NOT_FOUND;
                response.put("mensaje",msg);
            }
        }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }

        return new ResponseEntity<Map<String, Object>>(response,status);
    }

    @Override
    public ResponseEntity updateCliente(Cliente cliente, Long id, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            status = HttpStatus.BAD_REQUEST;
            msg = "Error al realizar la validacion de datos";
            response.put("mensaje", msg);
            response.put("error", errors);
        } else {
            try {
                Cliente cliActual = clientedao.findById(id).get();

                if (cliActual != null) {
                    cliActual.setNombre(cliente.getNombre());
                    cliActual.setApellido(cliente.getApellido());
                    cliActual.setEmail(cliente.getEmail());
                    cliActual = clientedao.save(cliActual);
                    status = HttpStatus.OK;
                    response.put("value", cliActual);
                } else {
                    msg = "No existe el cliente con id: " + id + " en la base de dato";
                    status = HttpStatus.NOT_FOUND;
                    response.put("mensaje", msg);
                }
            } catch (Exception e) {
                msg = "Error al realizar la consulta a la base de datos, Error:";
                error = e.getMessage();
                status = HttpStatus.INTERNAL_SERVER_ERROR;

                response.put("mensaje", msg);
                response.put("error", error);
            }
        }
        return new ResponseEntity<Map<String, Object>>(response,status);

    }

    @Override
    public ResponseEntity upload(MultipartFile archivo, Long id) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            if (!archivo.isEmpty()){
                Cliente cliente = clientedao.findById(id).get();
                if(cliente != null){
                    fileService.eliminar(cliente.getFoto());
                    cliente.setFoto(fileService.copiar(archivo));
                    clientedao.save(cliente);
                    status = HttpStatus.OK;
                    response.put("value", cliente);
                }else{
                    msg= "No existe el cliente con id: "+id+" en la base de dato";
                    status= HttpStatus.NOT_FOUND;
                    response.put("mensaje",msg);
                }
            }else{
                msg= "No existen archivos para procesar";
                status= HttpStatus.NOT_FOUND;
                response.put("mensaje",msg);
            }
        }catch(IOException e){
            msg= "Error al realizar el upload del archivo, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }

        return new ResponseEntity<Map<String, Object>>(response,status);
    }

    @Override
    public ResponseEntity verFoto(String nombreFoto) {
        Resource recurso= null;
        HttpHeaders cabecera= new HttpHeaders();
        try {
            log.info("Entre");

            recurso= fileService.cargar(nombreFoto);

            log.info("recurso: "+ recurso.getFilename());

            cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso+"\"");

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            String msg;
            String error;
            HttpStatus status;

            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);

        return new ResponseEntity<Map<String, Object>>(response,status);
        }

        return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllRegions() {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            List<Region> lista = (List<Region>) clientedao.findAllRegiones();
            log.info("cantidad de regiones: "+ lista.size());
            lista.forEach(region -> log.info(region.toString()));

            if (lista.size() > 0) {
                status= HttpStatus.OK;
                response.put("value",lista);
            }else{
                msg= "No existen regiones en la base de dato";
                status= HttpStatus.NOT_FOUND;
                response.put("mensaje",msg);
            }
        }catch (Exception e){
            msg= "Error al realizar la consulta a la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
        }

        return new ResponseEntity<Map<String, Object>>(response,status);
    }

    void deleteFoto(Cliente cliente){
        String fotoAnterior=  cliente.getFoto();

        if(fotoAnterior != null && !fotoAnterior.isEmpty()){
            Path rutaAnterior= Paths.get("C:\\Users\\Carlos\\Educacion\\Cursos\\Spring-Angular\\Angular_Spring_Gabriel-Guzman\\Seccion_2\\uploads").resolve(fotoAnterior).toAbsolutePath();
            File archivoAnterior= rutaAnterior.toFile();
            if(archivoAnterior.exists() && archivoAnterior.canRead()){
                archivoAnterior.delete();
            }
        }
    }

}
