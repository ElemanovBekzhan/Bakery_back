package org.example.subd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SubdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubdApplication.class, args);
    }

}
