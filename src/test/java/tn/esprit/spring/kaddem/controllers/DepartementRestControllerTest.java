package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.spring.kaddem.DepartementRestController;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(DepartementRestController.class)
 class DepartementRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDepartementService departementService;

    @InjectMocks
    private DepartementRestController departementRestController;

    @Test
     void testGetDepartements() throws Exception {
        List<Departement> departements = Arrays.asList(new Departement(1, "Dept1"), new Departement(2, "Dept2"));

        when(departementService.retrieveAllDepartements()).thenReturn(departements);

        mockMvc.perform(MockMvcRequestBuilders.get("/departement/retrieve-all-departements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Dept1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dept2"));
    }

    @Test
     void testRetrieveDepartement() throws Exception {
        Departement departement = new Departement(1, "Dept1");

        when(departementService.retrieveDepartement(1)).thenReturn(departement);

        mockMvc.perform(MockMvcRequestBuilders.get("/departement/retrieve-departement/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Dept1"));
    }

    @Test
     void testAddDepartement() throws Exception {
        Departement newDepartement = new Departement(3, "Dept3");

        when(departementService.addDepartement(any(Departement.class))).thenReturn(newDepartement);

        mockMvc.perform(MockMvcRequestBuilders.post("/departement/add-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Dept3\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Dept3"));
    }

    @Test
     void testRemoveDepartement() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/departement/remove-departement/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
     void testUpdateDepartement() throws Exception {
        Departement updatedDepartement = new Departement(1, "UpdatedDept");

        when(departementService.updateDepartement(any(Departement.class))).thenReturn(updatedDepartement);

        mockMvc.perform(MockMvcRequestBuilders.put("/departement/update-departement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"UpdatedDept\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UpdatedDept"));
    }
}
