package com.subjecttochange.ghhelper;

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

    private static final boolean PRINTING_BEANS = false;

    /**
     * @param args parameters to start application with
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Runs before the application starts
     * @param context of the application
     * @return inspection of project beans
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            if (PRINTING_BEANS) {
                System.out.println("Let's inspect the beans provided by Spring Boot:");

                String[] beanNames = context.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
            }
        };
    }

}