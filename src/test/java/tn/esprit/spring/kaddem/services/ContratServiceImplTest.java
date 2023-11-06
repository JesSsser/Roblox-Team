package tn.esprit.spring.kaddem.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

@ExtendWith(MockitoExtension.class)
class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Test
    void testRetrieveContrat() {
        // Arrange
        int contratId = 1;
        Contrat expectedContrat = new Contrat();
        Mockito.when(contratRepository.findById(contratId)).thenReturn(Optional.of(expectedContrat));

        // Act
        Contrat actualContrat = contratService.retrieveContrat(contratId);

        // Assert
        Assertions.assertEquals(expectedContrat, actualContrat);
    }

    @Test
    void testUpdateContrat() {
        // Arrange
        Contrat inputContrat = new Contrat();
        Contrat expectedContrat = new Contrat();
        Mockito.when(contratRepository.save(inputContrat)).thenReturn(expectedContrat);

        // Act
        Contrat updatedContrat = contratService.updateContrat(inputContrat);

        // Assert
        Assertions.assertEquals(expectedContrat, updatedContrat);
    }

    @Test
    public void testAddContrat() {
        // Create a Contrat object for testing
        Contrat contrat = new Contrat();

        // Mock the repository's save method
        Mockito.when(contratRepository.save(contrat)).thenReturn(contrat);

        // Call the service method
        Contrat addedContrat = contratService.addContrat(contrat);

        // Verify that the save method of the repository was called with the correct Contrat object
        Mockito.verify(contratRepository, Mockito.times(1)).save(contrat);

        // Assert that the returned Contrat is the same as the one returned by the repository
        Assertions.assertEquals(contrat, addedContrat);
    }
    @Test
    public void testRemoveContrat() {
        // Mock data
        Integer contratId = 1;
        Contrat contrat = new Contrat();

        // Mock the repository's findById and delete methods
        Mockito.when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));

        // Call the service method
        contratService.removeContrat(contratId);

        // Verify that the delete method of the repository was called with the correct Contrat object
        Mockito.verify(contratRepository, Mockito.times(1)).delete(contrat);
    }
}
