package com.renting.skirent.controller;

import com.renting.skirent.model.Client;
import com.renting.skirent.repository.ClientRepository;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
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

        String pesel = readPesel();
        client.setPesel(pesel);

        String number = readNumber();
        client.setContactNumber(number);

        return client;

    }

    private String readPesel() {
        System.out.println("Provide pesel (should consist of 11 digits):");
        do {
            String pesel = scanner.nextLine();
            if (pesel.length() == 11) {
                boolean contains = pesel.matches("[0-9]+");
                if (contains) {
                    Client client = repository.findByContactNumber(pesel);
                    if (client != null)
                        System.out.println("Client with this pesel already exist!\nTry again:");
                    else
                        return pesel;
                } else
                    System.out.println("Pesel should contains only digits!\nTry again:");
            } else
                System.out.println("Pesel should contains 11 digits!\nTry again:");
        }while (true);
    }

    private String readNumber() {
        System.out.println("Contact number (should consist of 9 digits):");
        do {
            String number = scanner.nextLine();
            if (number.length() == 9) {
                boolean contains = number.matches("[0-9]+");
                if (contains) {
                    Client client = repository.findByContactNumber(number);
                    if (client != null)
                        System.out.println("Client with this number already exist!\nTry again:");
                    else
                        return number;
                }else
                    System.out.println("Number should contains only digits!\nTry again:");
            }else
                System.out.println("Number should contains 9 digits!\nTry again:");
        }while(true);
    }

    /*
    Method for removing client from db
     */
    public void remove(){
        System.out.println("Provide phone number of the client to remove:");
        String number = scanner.nextLine();
        Optional<Client> client = repository.findByContactNumberContaining(number);
        client.ifPresentOrElse(repository::delete, ()-> System.out.println("There is no client with id " + number));
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

    public void updateClient(){
        System.out.println("Provide client phone number:");
        String number = scanner.nextLine();
        Client toChange = repository.findByContactNumber(number);

        if(toChange != null){
            Client changed = readClient();
            toChange.setFirstName(changed.getFirstName());
            toChange.setLastName(changed.getLastName());
            toChange.setPesel(changed.getPesel());
            toChange.setContactNumber(changed.getContactNumber());
            repository.save(toChange);
        }else
            System.out.printf("Number %s not found\n", number);

    }

}
