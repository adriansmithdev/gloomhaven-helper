package com.subjecttochange.ghhelper;

import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 *
 * @author subjecttochange
 * @version 1
 *
 * Runs the spring boot server
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("com.subjecttochange.ghhelper.*")
@ComponentScan(basePackages = { "com.subjecttochange.ghhelper.*" })
@EntityScan("com.subjecttochange.ghhelper.*")
public class Application {

    /**
     * @param args parameters to start application with
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}