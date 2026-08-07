package com.project;

import com.project.airport.Airport;
import com.project.airport.AirportRepository;
import com.project.city.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AirportEndpointTest extends BaseControllerTest {

    @MockBean
    private AirportRepository airportRepository;

    private City              testCity;
    private List<Airport>     testAirportList;
    private Airport           testAirport;

    @BeforeEach
    public void setupTestData() {
        testAirport = new Airport();
        testAirportList = new ArrayList<>();
        testCity = new City();

        testCity.setId(8L);
        testCity.setName("St. Anthony");
        testCity.setState("Newfoundland");
        testCity.setPopulation(543);

        testAirport.setId(2L);
        testAirport.setName("St. Anthony Airport");
        testAirport.setCode("ZZX");
        testAirport.setCity(testCity);

        testAirportList = Collections.singletonList(testAirport);
    }

    @Test
    public void testGetAllAirports() throws Exception {
        Mockito.when(airportRepository.findAll())
                .thenReturn(testAirportList);

        mockMvc.perform(get("/api/airports/getall"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(2));
    }

    @Test
    public void testGetAllAirports_IsEmpty() throws Exception{
        Mockito.when(airportRepository.findAll())
                .thenReturn(null);

        mockMvc.perform(get("/api/airports/getall"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAirportById() throws Exception {
        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAirport));

        mockMvc.perform(get("/api/airports/findby/id/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void testReturnAirportById_NotFound() throws Exception {
        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/airports/findby/id/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewAirport() throws Exception {
        String parseAirportToJson = objectMapper.writeValueAsString(testAirport);

        Mockito.when(airportRepository.save(ArgumentMatchers.any(Airport.class)))
                .thenReturn(testAirport);

        mockMvc.perform(post("/api/airports/createnew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAirportToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void testUpdateAirportById() throws Exception {
        String parseAirportToJson = objectMapper.writeValueAsString(testAirport);

        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAirport));

        Mockito.when(airportRepository.save(ArgumentMatchers.any(Airport.class)))
                .thenReturn(testAirport);

        mockMvc.perform(put("/api/airports/update/id/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAirportToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void testAirportById_NotFound() throws Exception {
        String parseAirportToJson = objectMapper.writeValueAsString(testAirport);

        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/airports/update/id/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAirportToJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAirportById() throws Exception {
        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAirport));

        mockMvc.perform(delete("/api/airports/delete/id/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAirportById_NotFound() throws Exception {
        Mockito.when(airportRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/airports/delete/id/99"))
                .andExpect(status().isNotFound());
    }
}
