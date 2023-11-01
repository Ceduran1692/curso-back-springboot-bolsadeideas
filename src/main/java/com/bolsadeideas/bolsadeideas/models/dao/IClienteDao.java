package com.bolsadeideas.bolsadeideas.models.dao;

import com.bolsadeideas.bolsadeideas.models.entity.Cliente;
import com.bolsadeideas.bolsadeideas.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente,Long> {

    @Query("select r from Region r")
    Iterable<Region> findAllRegiones();
}
