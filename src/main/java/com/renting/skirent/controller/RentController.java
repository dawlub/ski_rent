package com.renting.skirent.controller;

import com.renting.skirent.exceptions.IdNotFoundException;
import com.renting.skirent.exceptions.NoRentalsException;
import com.renting.skirent.exceptions.OutOfStockException;
import com.renting.skirent.model.Client;
import com.renting.skirent.model.Equipment;
import com.renting.skirent.repository.ClientRepository;
import com.renting.skirent.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentController {

    private Scanner scanner;
    private EquipmentRepository equipmentRepository;
    private ClientRepository clientRepository;

    public RentController(Scanner scanner, EquipmentRepository equipmentRepository, ClientRepository clientRepository) {
        this.scanner = scanner;
        this.equipmentRepository = equipmentRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void returnEquipment(){
        try {
            returnOfEquipment();
        }catch (IdNotFoundException | NoRentalsException e){
            e.getMessage();
        }
    }

    private void returnOfEquipment(){
        System.out.println("Provide client phone number");
        String number = scanner.nextLine();


        Optional<Client> client = Optional.ofNullable(clientRepository.findByContactNumber(number));

        if(client.isPresent()){
            List<Equipment> list = client.get().getRentedEquipment();

            if(list.size() > 0){
                System.out.println("Equipment in rent");
                for (Equipment equipment : list) {
                    System.out.printf("id: %d, name: %s\n",equipment.getId(), equipment.getName()) ;
                }

                System.out.println("Provide ID of goods for return:");
                long equipmentId = scanner.nextLong();
                Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

                if(equipment.isPresent()){
                    List<Equipment> rentedByClient = client.get().getRentedEquipment();

                    //Remove equipment from client rentals list
                    rentedByClient.remove(equipment.get());

                    //Remove client form equipment clients list
                    equipment.get().getClients().remove(client.get());
                    System.out.println("Equipment has been returned.");

                }else
                    throw new IdNotFoundException(String.format("No %d id of equipment in client rental list", equipmentId));
            }else
                throw new NoRentalsException(String.format("Client %s %s have nothing to return", client.get().getFirstName(), client.get().getLastName()));
        } else
            throw new IdNotFoundException(String.format("No client with %s phone number", number));
    }

    @Transactional
    public void rentEquipment(){

        try{
            rent();
        }catch(IdNotFoundException | OutOfStockException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Provide client phone number");
        String number = scanner.nextLine();

        System.out.println("Provide equipment ID");
        long equipmentId = scanner.nextLong();

        Optional<Client> client = Optional.ofNullable(clientRepository.findByContactNumber(number));
        Optional<Equipment> equipment = equipmentRepository.findById(equipmentId);

       if(client.isPresent()){

           if (equipment.isPresent()){

               if(equipment.get().getAmount() > equipment.get().getClients().size())
                   equipment.get().addClient(client.get());
               else
                   throw new OutOfStockException(String.format("%s out of stock", equipment.get().getName()));
           }else
               throw new IdNotFoundException(String.format("No equipment with %s id", equipmentId));
       }else
           throw new IdNotFoundException(String.format("No client with %s phone number", number));

        System.out.printf("%s has been rented for %s\n",equipment.get().getName(), client.get().getFirstName());
        System.out.printf("Amount to pay %.2f\n", equipment.get().getPrice());
    }


}
