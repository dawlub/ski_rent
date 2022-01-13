package com.renting.skirent.controller;

import com.renting.skirent.model.Client;
import com.renting.skirent.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ClientController {

    public Scanner scanner;
    public ClientRepository repository;

    public ClientController(Scanner scanner, ClientRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    /*
    Method responsible for adding new client to database
    *
     */
    public void createClient(){
        Client client = readClient();
        repository.save(client);
        System.out.printf("Client %s %s has been added\n", client.getFirstName(), client.getLastName());
    }


    /*
    Auxiliary method for reading data from the console and saving it in new object which is returned
     */
    private Client readClient() {
        Client client = new Client();

        System.out.println("Provide firstname of the client:");
        client.setFirstName(scanner.nextLine());

        System.out.println("Provide lastname of the client:");
        client.setLastName(scanner.nextLine());

        System.out.println("Provide pesel:");
        client.setPesel(scanner.nextLine());

        System.out.println("Contact number:");
        client.setContactNumber(scanner.nextLine());

       return client;

    }

    /*
    Method for removing client from db
     */
    public void remove(){
        System.out.println("Provide id of the client to remove:");
        long id = scanner.nextLong();
        Optional<Client> client = repository.findById(id);
        client.ifPresentOrElse(repository::delete, ()-> System.out.println("There is no client with id " + id));
    }

    public void searchClient(){
        System.out.println("Provide client phone number:");
        String number = scanner.nextLine();
        Client client = repository.findByContactNumber(number);
        if(client != null){
            System.out.printf("id %d, %s, %s, %s\n", client.getId(), client.getFirstName(), client.getLastName(), client.getContactNumber());
            client.getRentedEquipment().forEach(System.out::println);
        } else
            System.out.println("No client with phone number: " + number);
    }

}
