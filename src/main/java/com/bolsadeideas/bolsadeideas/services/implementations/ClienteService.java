package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.dao.IClienteDao;
import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteDao clientedao;

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
    @Transactional
    public ResponseEntity save(Cliente cliente) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            Cliente clienteNew = clientedao.save(cliente);
            status= HttpStatus.CREATED;
            response.put("value",clienteNew);

        }catch (Exception e){
            msg= "Error al realizar el impacto en la base de datos, Error:";
            error= e.getMessage();
            status= HttpStatus.INTERNAL_SERVER_ERROR;

            response.put("mensaje", msg);
            response.put("error", error);
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
    public ResponseEntity updateCliente(Cliente cliente, Long id) {
        Map<String, Object> response = new HashMap<>();
        String msg;
        String error;
        HttpStatus status;
        try {
            Cliente cliActual= clientedao.findById(id).get();

            if (cliActual != null) {
                cliActual.setNombre(cliente.getNombre());
                cliActual.setApellido(cliente.getApellido());
                cliActual.setEmail(cliente.getEmail());
                cliActual= clientedao.save(cliActual);
                status= HttpStatus.OK;
                response.put("value",cliActual);
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
}
