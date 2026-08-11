package com.project;

import com.project.aircraft.Aircraft;
import com.project.aircraft.AircraftRepository;
import com.project.passenger.Passenger;
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
public class AircraftEndpointTest extends BaseControllerTest {

    @MockBean
    private AircraftRepository aircraftRepository;

    private List<Aircraft> testAircraftList;
    private Aircraft testAircraft;
    private List<Passenger> testPassengerList;

    @BeforeEach
    public void setUpTestData(){
        testAircraftList = new ArrayList<>();
        testAircraft = new Aircraft();
        testPassengerList = new ArrayList<>();

        testAircraft.setId(7L);
        testAircraft.setAirlineName("Porter");
        testAircraft.setNumberOfPassengers(102);
        testAircraft.setType("boeing 727");
        testAircraft.setPassengers(testPassengerList);

        testAircraftList = Collections.singletonList(testAircraft);

    }

    @Test
    public void testGetAllAircraft() throws Exception {
        Mockito.when(aircraftRepository.findAll())
                .thenReturn(testAircraftList);

        mockMvc.perform(get("/api/aircraft/findall"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(7));
    }

    @Test
    public void testGetAllAircraft_IsEmpty() throws Exception{
        Mockito.when(aircraftRepository.findAll())
                .thenReturn(null);

        mockMvc.perform(get("/api/aircraft/findall"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAircraftById() throws Exception {
        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAircraft));

        mockMvc.perform(get("/api/aircraft/findby/id/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    public void testReturnAircraftById_NotFound() throws Exception {
        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/aircraft/findby/id/98"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewAircraft() throws Exception {
        String parseAircraftToJson = objectMapper.writeValueAsString(testAircraft);

        Mockito.when(aircraftRepository.save(ArgumentMatchers.any(Aircraft.class)))
                .thenReturn(testAircraft);

        mockMvc.perform(post("/api/aircraft/createnew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAircraftToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    public void testUpdateAircraftById() throws Exception {
        String parseAircraftToJson = objectMapper.writeValueAsString(testAircraft);

        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAircraft));

        Mockito.when(aircraftRepository.save(ArgumentMatchers.any(Aircraft.class)))
                .thenReturn(testAircraft);

        mockMvc.perform(put("/api/aircraft/updateby/id/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAircraftToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    public void testAircraftById_NotFound() throws Exception {
        String parseAircraftToJson = objectMapper.writeValueAsString(testAircraft);

        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/aircraft/update/id/98")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseAircraftToJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAircraftById() throws Exception {
        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testAircraft));

        mockMvc.perform(delete("/api/aircraft/deleteby/id/7"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteAircraftById_NotFound() throws Exception {
        Mockito.when(aircraftRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/airports/delete/id/98"))
                .andExpect(status().isNotFound());
    }

}
