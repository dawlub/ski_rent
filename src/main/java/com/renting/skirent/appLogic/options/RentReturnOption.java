package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;
import org.aspectj.weaver.loadtime.Options;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum RentReturnOption {
    RENT_EQUIPMENT(1,"Rent equipment"),
    RETURN_EQUIPMENT(2,"Return equipment"),
    EXIT_TO_MAIN(3,"Exit to main options");

    private int number;
    private String name;

    RentReturnOption(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static void printOption() {
        System.out.println("Renting options");
        Arrays.stream(RentReturnOption.values()).forEach(System.out::println);
    }

    public String toString(){
        return number + " - " + name;
    }

    public static RentReturnOption checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }


}