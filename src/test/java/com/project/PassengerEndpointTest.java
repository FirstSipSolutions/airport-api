package com.project;


import com.project.city.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import com.project.passenger.Passenger;
import com.project.passenger.PassengerRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PassengerEndpointTest extends BaseControllerTest {

    @MockBean
    private PassengerRepository passengerRepository;

    private final Passenger     testPassenger = new Passenger();
    private final City          testCity = new City();

    @BeforeEach
    public void setUpTestData() {
        testCity.setId(2);
        testCity.setName("St. John's");
        testCity.setState("Newfoundland");
        testCity.setPopulation(357829);

        testPassenger.setId(1);
        testPassenger.setFirstName("Rusty");
        testPassenger.setLastName("Shakleford");
        testPassenger.setPhoneNumber("7093211234");
        testPassenger.setCity(testCity);

        List<Passenger> testList = Collections.singletonList(testPassenger);
        Page<Passenger> testPage = new PageImpl<>(testList);

        Mockito.when(passengerRepository.findAll(ArgumentMatchers.any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(testPage);
    }

    @Test
    public void testGetAllPassengers() throws Exception {

        mockMvc.perform(get("/api/passengers/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1));
    }

    @Test
    public void testGetAllPassengers_IsEmpty() throws Exception{
        Mockito.when(passengerRepository.findAll(ArgumentMatchers.any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/api/passengers/getall"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testReturnPassengerById() throws Exception {
        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testPassenger));

        mockMvc.perform(get("/api/passengers/findby/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testReturnPassengerById_NotFound() throws Exception {
        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/passengers/findby/id/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewPassenger() throws Exception {
        String parsePassengerToJson = objectMapper.writeValueAsString(testPassenger);

        Mockito.when(passengerRepository.save(ArgumentMatchers.any(Passenger.class)))
                .thenReturn(testPassenger);

        mockMvc.perform(post("/api/passengers/createnew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parsePassengerToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdatePassengerById() throws Exception {
        String parsePassengerToJson = objectMapper.writeValueAsString(testPassenger);

        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testPassenger));

        Mockito.when(passengerRepository.save(ArgumentMatchers.any(Passenger.class)))
                .thenReturn(testPassenger);

        mockMvc.perform(put("/api/passengers/update/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parsePassengerToJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdatePassengerById_NotFound() throws Exception {
        String parsePassengerToJson = objectMapper.writeValueAsString(testPassenger);

        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/passengers/update/id/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parsePassengerToJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePassengerById() throws Exception {
        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testPassenger));

        mockMvc.perform(delete("/api/passengers/delete/id/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeletePassengerById_NotFound() throws Exception {
        Mockito.when(passengerRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/passengers/delete/id/5"))
                .andExpect(status().isNotFound());
    }
}
