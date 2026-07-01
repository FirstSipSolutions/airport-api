


/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */


package com.project.airport;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity


public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String state;
    private int population;


    @OneToMany(mappedBy = "city")
    private List<Airport> airports;




    public City() {
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

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public int getPopulation() {


        return population;
    }

    public void setPopulation(int population) {


        this.population = population;
    }

    // added in these for the listing airport getting aiport
    // getter and setter

    public List<Airport> getAirports() {

        return airports;

    }

    public void setAirports(List<Airport> airports) {

        this.airports = airports;

    }


}