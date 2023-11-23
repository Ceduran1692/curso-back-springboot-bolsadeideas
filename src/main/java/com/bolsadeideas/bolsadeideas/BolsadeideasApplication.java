package com.bolsadeideas.bolsadeideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BolsadeideasApplication {

    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(BolsadeideasApplication.class, args);
    }


    @Bean
    public CommandLineRunner passGenerator(){
        return args -> {
            System.out.println("clave admin: "+encoder.encode("admin1"));
            System.out.println("clave user: "+encoder.encode("user1"));
        };
    }
}
