



/*
 * FirstSipSolutions
 * Author: Chris/Justin
 * SD 15 - 2026
 */

package com.project.airport;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.city.City;
import com.project.flight.Flight;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties("airports")
    private City city;

    @OneToMany(mappedBy = "airportArrival")
    private List<Flight> arrivingFlights;

    @OneToMany(mappedBy = "airportDeparture")
    private List<Flight> departingFlights;

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