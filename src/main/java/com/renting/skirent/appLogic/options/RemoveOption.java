package com.renting.skirent.appLogic.options;

import com.renting.skirent.exceptions.InvalidOptionException;
import org.jboss.jandex.Main;

import java.util.Arrays;


public enum RemoveOption {
        REMOVE_EQUIPMENT(1, "Delete equipment"),
        REMOVE_CLIENT(2, "Delete client"),
        REMOVE_CATEGORY(3, "Delete category"),
        EXIT_TO_MAIN(4,"Exit to main options");

        private int number;
        private String name;

        RemoveOption(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public String toString() {
            return number + " - " + name;
        }

    public static void printOption() {
        System.out.println("Delete options");
        Arrays.stream(RemoveOption.values()).forEach(System.out::println);
    }

        public static RemoveOption checkOption(int number) {
            if (number < 1 || number > values().length)
                throw new InvalidOptionException("Option don't exist");
            return values()[number - 1];
        }

    }
