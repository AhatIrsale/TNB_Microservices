package com.example.redevabletnb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RedevableTnbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedevableTnbApplication.class, args);
    }


}
