package com.nineya.haloplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Halo-Plus main class.
 *
 * @author ryanwang
 * @date 2017-11-14
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Customize the spring config location
        System.setProperty("spring.config.additional-location",
            "optional:file:${user.home}/.halo-plus/,optional:file:${user.home}/halo-dev/");

        // Run application
        SpringApplication.run(Application.class, args);
    }

}
