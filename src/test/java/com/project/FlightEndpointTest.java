package com.project;

import com.project.flight.Flight;
import com.project.flight.FlightService;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public class FlightEndpointTest extends BaseControllerTest {

        @MockBean
        private FlightService flightService;

        private Flight testFlight;
        private List<Flight> testFlightList;

        @BeforeEach
        public void setUpTestDate(){
            testFlight = new Flight();
            testFlightList = new ArrayList<>();

            testFlight.setId(21L);
            testFlight.setGate("3B");
            testFlight.setFlightNumber("A383");
            testFlight.setTerminal("Air Canada");
            testFlight.setDeparture(LocalDateTime.of(2026, 10, 5, 14, 30));
            testFlight.setArrival(LocalDateTime.of(2026, 10, 6, 12, 30));
            testFlight.setStatus("On Time");

            testFlightList.add(testFlight);
        }

    @Test
    public void testGetAllFlights() throws Exception {
        Mockito.when(flightService.getAllFlights())
                .thenReturn(testFlightList);

        mockMvc.perform(get("/api/flight/findall"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(21));
    }

    @Test
    public void testGetAllFlight_IsEmpty() throws Exception{
        Mockito.when(flightService.getAllFlights())
                .thenReturn(null);

        mockMvc.perform(get("/api/flight/findall"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetFlightById() throws Exception {
        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(testFlight);

        mockMvc.perform(get("/api/flight/findby/id/21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(21));
    }

    @Test
    public void testGetFlightById_NotFound() throws Exception {
        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(null);

        mockMvc.perform(get("/api/flight/findby/id/98"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewFlight() throws Exception {
        String parseFlightToJson = objectMapper.writeValueAsString(testFlight);

        Mockito.when(flightService.createFlight(ArgumentMatchers.any(Flight.class)))
                .thenReturn(testFlight);

        mockMvc.perform(post("/api/flight/createnew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseFlightToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(21));
    }

    @Test
    public void testUpdateFlightById() throws Exception {
        String parseFlightToJson = objectMapper.writeValueAsString(testFlight);

        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(testFlight);

        Mockito.when(flightService.updateFlight(ArgumentMatchers.any(), ArgumentMatchers.any(Flight.class)))
                .thenReturn(testFlight);

        mockMvc.perform(put("/api/flight/update/id/21")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseFlightToJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(21));
    }

    @Test
    public void testFlightById_NotFound() throws Exception {
        String parseFlightToJson = objectMapper.writeValueAsString(testFlight);

        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(null);

        mockMvc.perform(put("/api/flight/update/id/98")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseFlightToJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFlightById() throws Exception {
        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(testFlight);

        mockMvc.perform(delete("/api/flight/delete/id/21"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteFlightById_NotFound() throws Exception {
        Mockito.when(flightService.getFlightById(ArgumentMatchers.any()))
                .thenReturn(null);

        mockMvc.perform(delete("/api/flight/delete/id/91"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetDeparturesByAirportId() throws Exception {
        Mockito.when(flightService.getDepartures(ArgumentMatchers.any()))
                .thenReturn(testFlightList);

        mockMvc.perform(get("/api/flight/departure/airportid/21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(21));
    }

    @Test
    public void testGetArrivalsByAirportId() throws Exception {
        Mockito.when(flightService.getArrivals((ArgumentMatchers.any())))
                .thenReturn(testFlightList);

        mockMvc.perform(get("/api/flight/arrival/airportid/21"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(21));
    }
}
