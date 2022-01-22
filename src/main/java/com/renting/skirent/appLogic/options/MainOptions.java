package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;

import java.util.Arrays;

public enum MainOptions  {

    RENT_RETURN(1,"Rent or return"),
    ADD(2,"Add data"),
    REMOVE(3,"Remove data"),
    SEARCH_BY_NAME(4,"Search by name"),
    UPDATE_DATA(5, "Update data"),
    EXIT(6,"Exit");

    private int number;
    private String name;


    MainOptions(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static void printOptions() {
        System.out.println("Main program options");
        Arrays.stream(MainOptions.values()).forEach(System.out::println);
    }

    public String toString(){
        return number + " - " + name;
    }

    public static MainOptions checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }

}