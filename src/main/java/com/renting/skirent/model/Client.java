package com.renting.skirent.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(length = 11, unique = true)
    private String pesel;
    @Column(name = "phone_number", length = 9, unique = true)
    private String contactNumber;
    @ManyToMany(mappedBy = "clients")
    private List<Equipment> rentedEquipment = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Equipment> getRentedEquipment() {
        return rentedEquipment;
    }

    public void setRentedEquipment(List<Equipment> rentedEquipment) {
        this.rentedEquipment = rentedEquipment;
    }


}
