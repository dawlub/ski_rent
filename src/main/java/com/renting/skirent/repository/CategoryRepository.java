package com.renting.skirent.repository;

import com.renting.skirent.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameContaining(String name);
    Optional<Category> findByNameIgnoreCase(String name);
}
