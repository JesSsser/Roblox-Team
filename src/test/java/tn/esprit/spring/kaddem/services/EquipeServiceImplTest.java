package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testRetrieveAllEquipes() {
        // Arrange
        Equipe equipe1 = new Equipe(); // Set properties as needed
        Equipe equipe2 = new Equipe();
        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        // Act
        List<Equipe> result = equipeService.retrieveAllEquipes();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(equipeRepository).findAll();
    }

    @Test
     void testAddEquipe() {
        // Arrange
        Equipe equipe = new Equipe(); // Set properties as needed
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        // Act
        Equipe result = equipeService.addEquipe(equipe);

        // Assert
        assertNotNull(result);
        verify(equipeRepository).save(equipe);
    }

    @Test
     void testRetrieveEquipe() {
        // Arrange
        int equipeId = 1;
        Optional<Equipe> equipe = Optional.of(new Equipe());
        when(equipeRepository.findById(equipeId)).thenReturn(equipe);

        // Act
        Equipe result = equipeService.retrieveEquipe(equipeId);

        // Assert
        assertNotNull(result);
        verify(equipeRepository).findById(equipeId);
    }

    @Test
     void testRetrieveEquipeNotFound() {
        // Arrange
        int equipeId = 1;
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> equipeService.retrieveEquipe(equipeId));
    }

    // Add more tests for updateEquipe, deleteEquipe, evoluerEquipes, etc.
}
