package com.renting.skirent.repository;

import com.renting.skirent.model.Category;
import com.renting.skirent.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    //@Query("SELECT e FROM Equipment e WHERE e.category.name = :category AND ")
    List<Equipment> findAllByCategoryAndSize(Category category, String size);
}

