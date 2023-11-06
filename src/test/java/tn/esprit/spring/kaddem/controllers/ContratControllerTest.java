package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.kaddem.controllers.ContratRestController;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContratRestController.class)
class ContratControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContratServiceImpl contratService;

    @Test
    void getContrat_ShouldReturnContrat() throws Exception {
        // Arrange
        int contratId = 1;
        Contrat contrat = new Contrat();
        given(contratService.retrieveContrat(contratId)).willReturn(contrat);

        // Act & Assert
        mockMvc.perform(get("/contrat/retrieve-contrat/{contrat-id}", contratId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(contrat.getIdContrat())); // Adjust the field names based on your Contrat class
    }

    @Test
    void addContrat_ShouldAddContrat() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        given(contratService.addContrat(any(Contrat.class))).willReturn(contrat);

        // Act & Assert
        mockMvc.perform(post("/contrat/add-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(contrat.getIdContrat())); // Adjust the field names based on your Contrat class
    }

    @Test
    void updateContrat_ShouldUpdateContrat() throws Exception {
        // Arrange
        Contrat contrat = new Contrat();
        given(contratService.updateContrat(any(Contrat.class))).willReturn(contrat);

        // Act & Assert
        mockMvc.perform(put("/contrat/update-contrat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idContrat").value(contrat.getIdContrat())); // Adjust the field names based on your Contrat class
    }

    @Test
    void removeContrat_ShouldRemoveContrat() throws Exception {
        // Arrange
        int contratId = 1;
        doNothing().when(contratService).removeContrat(contratId);

        // Act & Assert
        mockMvc.perform(delete("/contrat/remove-contrat/{contrat-id}", contratId))
                .andExpect(status().isOk());
    }

    @Test
    void getAllContrats_ShouldReturnAllContrats() throws Exception {
        // Arrange
        Contrat contrat1 = new Contrat();
        Contrat contrat2 = new Contrat();
        given(contratService.retrieveAllContrats()).willReturn(Arrays.asList(contrat1, contrat2));

        // Act & Assert
        mockMvc.perform(get("/contrat/retrieve-all-contrats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].idContrat").value(contrat1.getIdContrat())) // Adjust the field names based on your Contrat class
                .andExpect(jsonPath("$[1].idContrat").value(contrat2.getIdContrat())); // Adjust the field names based on your Contrat class
    }
}
