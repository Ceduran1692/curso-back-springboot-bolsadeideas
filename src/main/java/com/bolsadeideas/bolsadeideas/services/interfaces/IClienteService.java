package com.bolsadeideas.bolsadeideas.services.interfaces;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService{
    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Cliente findById(Long id);

    void delete(Long id);

    Cliente updateCliente(Cliente cliente, Long id);
}
