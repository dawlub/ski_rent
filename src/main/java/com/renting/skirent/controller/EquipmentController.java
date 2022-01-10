package com.renting.skirent.controller;

import com.renting.skirent.model.Category;
import com.renting.skirent.model.Equipment;
import com.renting.skirent.repository.CategoryRepository;
import com.renting.skirent.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

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

    public void createEquipment(){
        Equipment equipment = readEquipment();
        equipmentRep.save(equipment);
        System.out.printf("Equipment '%s' has been created:", equipment.getName());

    }

    private Equipment readEquipment() {
        Equipment equipment = new Equipment();

        System.out.println("Provide name of equipment:");
        equipment.setName(scanner.nextLine());

        System.out.println("Description of equipment:");
        equipment.setName(scanner.nextLine());

        System.out.println("Equipment price:");
        equipment.setPrice(scanner.nextDouble());

        equipment.setName("Size:");
        equipment.setSize(scanner.nextLine());

        System.out.println("Amount:");
        equipment.setName(scanner.nextLine());

        System.out.println("Category: ");
        long id = scanner.nextLong();
        Optional<Category> category = categoryRep.findById(id);
        scanner.nextLine();
        category.ifPresentOrElse(equipment::setCategory, () -> System.out.printf("Kategoria o id %d nie istnieje ", id));
        return equipment;
    }

    public void removeEquipment(){
        System.out.println("Provide id of equipment for delete");
        long id = scanner.nextLong();

        Optional<Equipment> equipment = equipmentRep.findById(id);
        equipment.ifPresentOrElse(equipmentRep::delete, () -> System.out.printf("Equipment with id %d not found", id));
    }

}
