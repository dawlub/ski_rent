package com.renting.skirent.repository;

import com.renting.skirent.model.Category;
import com.renting.skirent.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findAllByCategoryAndSize(Optional<Category> category, String size);
    Optional<Equipment> findByNameContaining(String name);
    List<Equipment> findAllByClientsIsNotNull();
}

