package com.renting.skirent.repository;

import com.renting.skirent.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {
    Optional<Client> findByContactNumberContaining(String number);
    Client  findByContactNumber(String number);
}
