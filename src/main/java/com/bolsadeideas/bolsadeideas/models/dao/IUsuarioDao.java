package com.bolsadeideas.bolsadeideas.models.dao;

import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);
}
