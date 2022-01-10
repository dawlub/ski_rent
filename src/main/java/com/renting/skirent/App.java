package com.renting.skirent;

import com.renting.skirent.model.Category;
import com.renting.skirent.model.Client;
import com.renting.skirent.model.Equipment;
import com.renting.skirent.repository.EquipmentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        EquipmentRepository equipmentRepository = run.getBean(EquipmentRepository.class);

        Equipment skis = new Equipment();
        skis.setName("Blizzard XP-890 ");
        skis.setDescription("Head skis for advanced");
        skis.setSize("156");
        skis.setAmount(5);
        skis.setPrice(25);

        Category category = new Category();
        category.setName("Skis");
        category.setDescription("All skis for adult");

        Client client = new Client();
        client.setFirstName("Bronisław");
        client.setLastName("Nowak");
        client.setPesel("78442369752");
        client.setContactNumber("874963321");

        skis.setCategory(category);

        skis.addClient(client);

        equipmentRepository.save(skis);
    }

    @Bean
    Scanner scanner(){
        return new Scanner(System.in);
    }

}