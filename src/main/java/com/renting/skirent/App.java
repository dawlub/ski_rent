package com.renting.skirent;

import com.renting.skirent.appLogic.AppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.util.Scanner;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        AppController bean = run.getBean(AppController.class);
        bean.mainLoop();
    }

    @Bean
    Scanner scanner(){
        return new Scanner(System.in);
    }

}