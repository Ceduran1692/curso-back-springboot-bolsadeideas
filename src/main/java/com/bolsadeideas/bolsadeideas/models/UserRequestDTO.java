package com.bolsadeideas.bolsadeideas.models;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRequestDTO {

    private String username;

    private String password;

}
