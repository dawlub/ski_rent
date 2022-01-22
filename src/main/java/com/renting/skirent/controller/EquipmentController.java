package com.renting.skirent.controller;

import com.renting.skirent.model.Category;
import com.renting.skirent.model.Equipment;
import com.renting.skirent.repository.CategoryRepository;
import com.renting.skirent.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EquipmentController {

        private Scanner scanner;
        private EquipmentRepository equipmentRep;
        private CategoryRepository categoryRep;

    public EquipmentController(Scanner scanner, EquipmentRepository equipmentRep, CategoryRepository categoryRep) {
        this.scanner = scanner;
        this.equipmentRep = equipmentRep;
        this.categoryRep = categoryRep;
    }

    //I have to use transactional because I'm trying to use lazy data after closing transaction,
    // and it's providing to exception
    @Transactional
    public void createEquipment(){
        Equipment equipment = readEquipment();
        equipmentRep.save(equipment);
        System.out.printf("Equipment '%s' has been created:\n", equipment.getName());

    }

    private Equipment readEquipment() {
        Equipment equipment = new Equipment();

        System.out.println("Provide name of equipment:");
        equipment.setName(scanner.nextLine());

        System.out.println("Description of equipment:");
        equipment.setDescription(scanner.nextLine());

        System.out.println("Equipment price:");
        equipment.setPrice(scanner.nextDouble());
        scanner.nextLine();

        System.out.println("Size:");
        equipment.setSize(scanner.nextLine());

        System.out.println("Amount:");
        equipment.setAmount(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Category: ");
        String catName = scanner.nextLine();
        Optional<Category> category = categoryRep.findByNameIgnoreCase(catName);
        category.ifPresentOrElse(equipment::setCategory, () -> System.out.printf("Kategoria %s nie istnieje", catName));
        return equipment;
    }

    public void removeEquipment(){
        System.out.println("Provide id of equipment for delete");
        long id = scanner.nextLong();

        Optional<Equipment> equipment = equipmentRep.findById(id);
        equipment.ifPresentOrElse(equipmentRep::delete, () -> System.out.printf("Equipment with id %d not found\n", id));
    }

    @Transactional
    public void searchEquipment(){
        System.out.println("Provide category:");
        String catName = scanner.nextLine();

        System.out.println("Provide size:");
        String size = scanner.nextLine();

        Optional<Category> category = categoryRep.findCategoryByNameContains(catName);

        if(category.isPresent()){
            List<Equipment> list = equipmentRep.findAllByCategoryAndSize(category, size);

            if(!list.isEmpty()){
                list.forEach(x -> System.out.printf("%s  available amount %d\n", x.toString(), x.getAmount() - x.getClients().size()));
            }else
                System.out.println("Equipment with specified size is unavailable");
        } else
            System.out.println("Category not exist");

    }
    @Transactional
    public void rentedEquipment() {
        List<Equipment> inRent = equipmentRep.findAllByClientsIsNotNull();

        if(inRent.isEmpty()){
            System.out.println("No equipment in rent:");
        }else {
            System.out.println("Equipment in rent:");
            inRent.forEach(x -> System.out.printf("%s  quantity in rent %d\n", x.toString(), x.getClients().size()));
        }
    }

    public void updateEquipment(){
        System.out.println("Provide name of the equipment for update:");
        String name = scanner.nextLine();
        Optional<Equipment> toUpdate = equipmentRep.findByNameContaining(name);

        if(toUpdate.isPresent()){
            Equipment toSet = toUpdate.get();
            Equipment equipment = readEquipment();
            toSet.setName(equipment.getName());
            toSet.setDescription(equipment.getDescription());
            toSet.setAmount(equipment.getAmount());
            toSet.setPrice(equipment.getPrice());
            toSet.setSize(equipment.getSize());
            toSet.setCategory(equipment.getCategory());
            equipmentRep.save(toSet);
        } else
            System.out.printf("Equipment %s not found\n", toUpdate);

    }

}
