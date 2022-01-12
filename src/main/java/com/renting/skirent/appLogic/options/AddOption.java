package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;

import java.util.Arrays;

public enum AddOption {
    ADD_EQUIPMENT(1,"Add equipment"),
    ADD_CLIENT(2,"Add client"),
    ADD_CATEGORY(3,"Add category"),
    EXIT_TO_MAIN(4, "Exit to main");

    private int number;
    private String name;

    AddOption(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String toString(){
        return number + " - " + name;
    }

    public static AddOption checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }


    public static void printOption() {
        System.out.println("Add options");
        Arrays.stream(AddOption.values()).forEach(System.out::println);
    }
}