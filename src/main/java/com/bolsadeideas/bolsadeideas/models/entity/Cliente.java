package com.bolsadeideas.bolsadeideas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El campo nombre no puede ser vacio")
    @Size(min = 3, max = 50)
    private String nombre;

    @NotEmpty(message = "El campo apellido no puede ser vacio")
    @Column(nullable = false )
    private String apellido;

    @NotEmpty(message = "El campo email no puede ser vacio")
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull(message = "El campo no puede ser nulo")
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @NotNull(message = "La region no puede ser nula")
    @JsonIgnoreProperties({"HibernateLazyInitializer","handler"})
    private Region region;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    //@PrePersist
    void prePersist(){
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


}
