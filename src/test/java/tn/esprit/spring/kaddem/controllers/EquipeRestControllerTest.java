package tn.esprit.spring.kaddem.controllers;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipeRestController.class)
class EquipeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEquipeService equipeService;

    @Test
    void getEquipes_ShouldReturnAllEquipes() throws Exception {
        // Arrange
        List<Equipe> equipes = List.of(new Equipe(), new Equipe());
        given(equipeService.retrieveAllEquipes()).willReturn(equipes);

        // Act & Assert
        mockMvc.perform(get("/equipe/retrieve-all-equipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void retrieveEquipe_ShouldReturnEquipe() throws Exception {
        // Arrange
        Equipe equipe = new Equipe(); // Set properties as needed
        given(equipeService.retrieveEquipe(any(Integer.class))).willReturn(equipe);

        // Act & Assert
        mockMvc.perform(get("/equipe/retrieve-equipe/{equipe-id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void addEquipe_ShouldAddEquipe() throws Exception {
        // Arrange
        Equipe equipe = new Equipe();
        given(equipeService.addEquipe(any(Equipe.class))).willReturn(equipe);

        // Act & Assert
        mockMvc.perform(post("/equipe/add-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void removeEquipe_ShouldRemoveEquipe() throws Exception {
        // Arrange
        doNothing().when(equipeService).deleteEquipe(any(Integer.class));

        // Act & Assert
        mockMvc.perform(delete("/equipe/remove-equipe/{equipe-id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void updateEquipe_ShouldUpdateEquipe() throws Exception {
        // Arrange
        Equipe equipe = new Equipe();
        given(equipeService.updateEquipe(any(Equipe.class))).willReturn(equipe);

        // Act & Assert
        mockMvc.perform(put("/equipe/update-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }
}
