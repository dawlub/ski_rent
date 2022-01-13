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

        Category category = categoryRep.findByNameContaining(catName);

        if(category != null){
            List<Equipment> list = equipmentRep.findAllByCategoryAndSize(category, size);

            if(!list.isEmpty()){
                list.forEach(System.out::println);
            }else
                System.out.println("Equipment with specified size is unavailable");
        } else
            System.out.println("Category not exist");

    }

}
