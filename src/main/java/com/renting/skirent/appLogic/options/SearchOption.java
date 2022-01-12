package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;

public enum SearchOption{
    SEARCH_CLIENT_BY_NAME(2,"Search client by name"),
    SEARCH_EQUIPMENT_BY_NAME(2,"Search equipment by name"),
    SEARCH_CATEGORY_BY_NAME(2,"Search category by name");

    private int number;
    private String name;

    SearchOption(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String toString(){
        return number + " - " + name;
    }

    static SearchOption checkOption(int number){
        if(number < 1 || number > values().length)
            throw new InvalidOptionException("Option don't exist");
        return values()[number-1];
    }

}