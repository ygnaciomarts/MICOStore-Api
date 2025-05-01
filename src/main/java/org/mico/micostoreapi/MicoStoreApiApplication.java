package org.mico.micostoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MicoStoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicoStoreApiApplication.class, args);
    }
}