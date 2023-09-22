package com.bolsadeideas.bolsadeideas.services.implementations;

import com.bolsadeideas.bolsadeideas.models.dao.IClienteDao;
import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.services.interfaces.IClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteDao clientedao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clientedao.findAll();

    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clientedao.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return clientedao.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        try {
            Cliente cliente = this.findById(id);
            clientedao.delete(cliente);
        }catch (Exception e){
            log.info("Error: "+ e.getMessage());
        }

    }

    @Override
    public Cliente updateCliente(Cliente cliente, Long id) {
        Cliente cliActual= this.findById(id);

        cliActual.setNombre(cliente.getNombre());
        cliActual.setApellido(cliente.getApellido());
        cliActual.setEmail(cliente.getEmail());

        return clientedao.save(cliActual);
    }
}
