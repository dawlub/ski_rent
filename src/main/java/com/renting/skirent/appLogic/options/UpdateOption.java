package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;

import java.util.Arrays;

public enum UpdateOption {
    UPDATE_EQUIPMENT(1, "Update equipment: "),
    UPDATE_CLIENT(2, "Update client: "),
    UPDATE_CATEGORY(3, "Update category"),
    EXIT_TO_MAIN(4, "Exit to main option");

    private int number;
    private String name;

    UpdateOption(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String toString(){
        return number + " - " + name;
    }

    public static void printOption() {
        System.out.println("Searching option");
        Arrays.stream(UpdateOption.values()).forEach(System.out::println);
    }

    public static UpdateOption checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }
}
