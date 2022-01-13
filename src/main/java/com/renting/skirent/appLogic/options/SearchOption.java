package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;

import java.util.Arrays;

public enum SearchOption{
    SEARCH_CLIENT_BY_PHONE_NUMBER(1,"Search client by phone number"),
    SEARCH_EQUIPMENT_BY_SIZE_AND_CATEGORY(2,"Search equipment by size and category"),
    SEARCH_CATEGORY_BY_NAME(3,"Search category by name"),
    EXIT_TO_MAIN(4,"Exit to main option");

    private int number;
    private String name;

    SearchOption(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String toString(){
        return number + " - " + name;
    }

    public static void printOption() {
        System.out.println("Searching option");
        Arrays.stream(SearchOption.values()).forEach(System.out::println);
    }

    public static SearchOption checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }

}