package com.project.airport;


<<<<<<< Updated upstream:src/main/java/com/project/airport/Airport.java
// first sip solutions
// will add comments asap
// But  here is the com.project.airport java class and entity

=======
/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */
>>>>>>> Stashed changes:src/main/java/airport/Airport.java

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    private City city;

    public Airport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}