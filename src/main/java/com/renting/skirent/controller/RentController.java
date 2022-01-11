package com.renting.skirent.controller;

import com.renting.skirent.exceptions.IdNotFoundException;
import com.renting.skirent.exceptions.OutOfStockException;
import com.renting.skirent.model.Client;
import com.renting.skirent.model.Equipment;
import com.renting.skirent.repository.ClientRepository;
import com.renting.skirent.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

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

    public void rentEquipment(){

        try{
            rent();
        }catch(IdNotFoundException | OutOfStockException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Provide client ID");
        long clientId = scanner.nextLong();

        System.out.println("Provide equipment ID");
        long equipmentId = scanner.nextLong();

        Optional<Client> client = clientRepository.findById(clientId);
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
           throw new IdNotFoundException(String.format("No client with %s id", clientId));
    }


}
