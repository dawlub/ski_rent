package com.renting.skirent.appLogic;

import com.renting.skirent.appLogic.options.*;
import com.renting.skirent.controller.CategoryController;
import com.renting.skirent.controller.ClientController;
import com.renting.skirent.controller.EquipmentController;
import com.renting.skirent.controller.RentController;
import com.renting.skirent.exceptions.InvalidOptionException;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

@Controller
public class AppController  {

    private Scanner scanner;
    private CategoryController categoryController;
    private ClientController clientController;
    private EquipmentController equipmentController;
    private RentController rentController;


    public AppController(Scanner scanner, CategoryController categoryController, ClientController clientController,
                         EquipmentController equipmentController, RentController rentController) {
        this.scanner = scanner;
        this.categoryController = categoryController;
        this.clientController = clientController;
        this.equipmentController = equipmentController;
        this.rentController = rentController;
    }

    public void mainLoop(){
        MainOptions option;

        do{
            MainOptions.printOptions();
            option = readOption();
            mainSwitch(option);

        }while(option != MainOptions.EXIT);
    }

    private void mainSwitch(MainOptions option) {
        switch(option){
            case RENT_RETURN -> rentReturnLoop();
            case ADD -> addLoop();
            case REMOVE -> removeLoop();
            case SEARCH_BY_NAME -> searchByNameLoop();
            case EXIT -> exit();
            default -> throw new InvalidOptionException("Main option not found");
        }
    }

    private void rentReturnSwitch(RentReturnOption rentReturnOption){
        switch (rentReturnOption){
            case RENT_EQUIPMENT -> rentController.rentEquipment();
            case RETURN_EQUIPMENT -> rentController.returnEquipment();
            case EXIT_TO_MAIN -> mainLoop();
            default -> throw new InvalidOptionException("Option not found");
        }
    }

    private void addSwitch(AddOption addOption){
        switch (addOption){
            case ADD_EQUIPMENT -> equipmentController.createEquipment();
            case ADD_CLIENT -> clientController.createClient();
            case ADD_CATEGORY -> categoryController.createCategory();
            case EXIT_TO_MAIN -> mainLoop();
            default -> throw new InvalidOptionException("Option not found");
        }
    }

    private void removeSwitch(RemoveOption removeOption){
        switch (removeOption) {
            case REMOVE_EQUIPMENT -> equipmentController.removeEquipment();
            case REMOVE_CLIENT -> clientController.remove();
            case REMOVE_CATEGORY -> categoryController.remove();
            case EXIT_TO_MAIN -> mainLoop();
            default -> throw new InvalidOptionException("Option not found");
        }
    }

    private void searchSwitch(SearchOption searchOption){
        switch (searchOption){
            case SEARCH_CLIENT_BY_PHONE_NUMBER -> clientController.searchClient();
            case SEARCH_EQUIPMENT_BY_SIZE_AND_CATEGORY -> equipmentController.searchEquipment();
            case EXIT_TO_MAIN -> mainLoop();
            default -> throw new InvalidOptionException("Option not found");
        }
    }

    private MainOptions readOption() {

        MainOptions option = null;
        boolean flagOption = false;

        while(!flagOption) {
            System.out.println("Provide number of menu option:");
            int id = scanner.nextInt();

            try{
                option = MainOptions.checkOption(id);
                flagOption = true;
            }catch (InvalidOptionException e){
                System.err.println(e);
            }
        }
        return option;
    }
    private RentReturnOption readRentReturn() {
        RentReturnOption option = null;
        boolean flagOption = false;

        while(!flagOption) {
            System.out.println("Provide number of menu option:");
            int id = scanner.nextInt();
            scanner.nextLine();
            try{
                option = RentReturnOption.checkOption(id);
                flagOption = true;
            }catch (InvalidOptionException e){
                System.err.println(e);
            }
        }
        return option;
    }

    private AddOption readAddOption() {
        AddOption option = null;
        boolean flagOption = false;

        while(!flagOption) {
            System.out.println("Provide number  of add option:");
            int id = scanner.nextInt();
            scanner.nextLine();

            try{
                option = AddOption.checkOption(id);
                flagOption = true;
            }catch (InvalidOptionException e){
                System.err.println(e);
            }
        }
        return option;
    }

    private RemoveOption readRemoveOption(){
        RemoveOption option = null;
        boolean flagOption = false;

        while(!flagOption) {
            System.out.println("Provide number of delete option:");
            int id = scanner.nextInt();
            scanner.nextLine();

            try{
                option = RemoveOption.checkOption(id);
                flagOption = true;
            }catch (InvalidOptionException e){
                System.err.println(e);
            }
        }
        return option;
    }
    private SearchOption readSearchOption(){
        SearchOption option = null;
        boolean flagOption = false;

        while(!flagOption) {
            System.out.println("Provide number of search option:");
            int id = scanner.nextInt();
            scanner.nextLine();

            try{
                option = SearchOption.checkOption(id);
                flagOption = true;
            }catch (InvalidOptionException e){
                System.err.println(e);
            }
        }
        return option;
    }


    private void rentReturnLoop() {
        RentReturnOption option;
        do {
            RentReturnOption.printOption();
            option = readRentReturn();
            rentReturnSwitch(option);
        } while (option != RentReturnOption.EXIT_TO_MAIN);
    }

    private void addLoop() {
        AddOption option;
        do {
            AddOption.printOption();
            option = readAddOption();
            addSwitch(option);
        } while (option != AddOption.EXIT_TO_MAIN);
    }

    private void removeLoop() {
        RemoveOption option;
        do {
            RemoveOption.printOption();
            option = readRemoveOption();
            removeSwitch(option);
        } while (option != RemoveOption.EXIT_TO_MAIN);
    }

    private void searchByNameLoop() {
        SearchOption option;
        do{
            SearchOption.printOption();
            option = readSearchOption();
            searchSwitch(option);
        }while (option != SearchOption.EXIT_TO_MAIN);
    }


    private void exit(){
        scanner.close();
        System.out.println("App has been closed");
        System.exit(0);
    }

}
