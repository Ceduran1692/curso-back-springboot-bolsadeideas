package com.bolsadeideas.bolsadeideas.models.dao;

import com.bolsadeideas.bolsadeideas.models.entity.Role;
import com.bolsadeideas.bolsadeideas.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("select u.roles from Usuario u where u.username = :username")
    List<Role> findRolesByUsername(String username);

}
