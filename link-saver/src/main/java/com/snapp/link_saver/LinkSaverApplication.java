package com.snapp.link_saver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LinkSaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkSaverApplication.class, args);
    }

}
