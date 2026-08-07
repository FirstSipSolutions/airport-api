package com.project;

import com.project.city.City;
import com.project.city.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityEndpointTest extends BaseControllerTest {

    @MockBean
    private CityRepository cityRepository;

    private City testCity;
    private List<City> testCityList;

    @BeforeEach
    public void setUpTestData() {
        testCity = new City();

        testCity.setId(5);
        testCity.setName("Deer Lake");
        testCity.setState("Newfoundland");
        testCity.setPopulation(8567);

        testCityList = Collections.singletonList(testCity);

    }

    @Test
    public void testGetAllCitiesPaged() throws Exception {
        Page<City> testPage = new PageImpl<>(testCityList);

        Mockito.when(cityRepository.findAll(ArgumentMatchers.any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(testPage);

        mockMvc.perform(get("/api/cities/getall/paged"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(5));
    }

    @Test
    public void testGetAllCities() throws Exception {
        Mockito.when(cityRepository.findAll())
                .thenReturn(testCityList);

        mockMvc.perform(get("/api/cities/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value(5));
    }

    @Test
    public void testGetAllCities_IsEmpty() throws Exception{
        Mockito.when(cityRepository.findAll(ArgumentMatchers.any(org.springframework.data.domain.Pageable.class)))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/api/cities/getall/paged"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCityById() throws Exception {
        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testCity));

        mockMvc.perform(get("/api/cities/findby/id/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(5));
    }

    @Test
    public void testReturnCityById_NotFound() throws Exception {
        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cities/findby/id/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewCity() throws Exception {
        String parseCityToJson = objectMapper.writeValueAsString(testCity);

        Mockito.when(cityRepository.save(ArgumentMatchers.any(City.class)))
                .thenReturn(testCity);

        mockMvc.perform(post("/api/cities/createnew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseCityToJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    public void testUpdateCityById() throws Exception {
        String parseCityToJson = objectMapper.writeValueAsString(testCity);

        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testCity));

        Mockito.when(cityRepository.save(ArgumentMatchers.any(City.class)))
                .thenReturn(testCity);

        mockMvc.perform(put("/api/cities/update/id/5")
                 .contentType(MediaType.APPLICATION_JSON)
                .content(parseCityToJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    public void testUpdateCityById_NotFound() throws Exception {
        String parseCityToJson = objectMapper.writeValueAsString(testCity);

        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/api/cities/update/id/99") // Using 99 to represent a non-existent ID
                .contentType(MediaType.APPLICATION_JSON)
                .content(parseCityToJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCityById() throws Exception {
        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(testCity));

        mockMvc.perform(delete("/api/cities/delete/id/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteCityById_NotFound() throws Exception {
        Mockito.when(cityRepository.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/cities/delete/id/99"))
                .andExpect(status().isNotFound());
    }
}
