package com.renting.skirent.controller;


import com.renting.skirent.model.Category;
import com.renting.skirent.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryController {

    private Scanner scanner;
    private CategoryRepository repository;

    public CategoryController(Scanner scanner, CategoryRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    /*
    Method responsible for adding new category to database
     */
    public void createCategory(){
        Category category = readCategory();
        try {
            repository.save(category);
            System.out.printf("Category '%s' has been created:\n", category.getName());
        }catch (DataIntegrityViolationException e){
            System.err.println("Category already exist");
        }
    }

    /*
     Auxiliary method for reading data from the console and saving it in new object which is returned
   */
    private Category readCategory() {
        Category category = new Category();

        System.out.println("Provide name of category:");
        category.setName(scanner.nextLine());

        System.out.println("Provide description:");
        category.setDescription(scanner.nextLine());

        return category;
    }

    /*
    Method for removing category from db
     */
    public void remove(){
        System.out.println("Provide id of category to remove:");
        long id = scanner.nextLong();
        Optional<Category> category = repository.findById(id);
        category.ifPresentOrElse(repository::delete, () -> System.out.println("There is no category with id " + id));

    }

//    public void searchCategory(){
//        System.out.println("Provide name of category:");
//        String categoryName = scanner.nextLine();
//        Category category = repository.findByNameContaining(categoryName);
//        equi
//    }


}
