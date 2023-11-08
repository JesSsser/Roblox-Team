package tn.esprit.spring.kaddem.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

public class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Mock the behavior of the repository
        when(departementRepository.findAll()).thenReturn(Arrays.asList(
                new Departement(1, "Dept1"),
                new Departement(2, "Dept2")
        ));

        List<Departement> departements = departementService.retrieveAllDepartements();

        // Assert the result
        assertEquals(2, departements.size());
        assertEquals("Dept1", departements.get(0).getNomDepart());
        assertEquals("Dept2", departements.get(1).getNomDepart());
    }

    @Test
    public void testAddDepartement() {
        Departement newDepartement = new Departement("Dept3");


        when(departementRepository.save(newDepartement)).thenReturn(newDepartement);

        Departement addedDepartement = departementService.addDepartement(newDepartement);


        assertNotNull(addedDepartement);
        assertEquals("Dept3", addedDepartement.getNomDepart());
    }

    @Test
    public void testUpdateDepartement() {
        Departement existingDepartement = new Departement(4, "Dept4");


        when(departementRepository.save(existingDepartement)).thenReturn(existingDepartement);

        Departement updatedDepartement = departementService.updateDepartement(existingDepartement);


        assertNotNull(updatedDepartement);
        assertEquals(4, updatedDepartement.getIdDepart().intValue());
        assertEquals("Dept4", updatedDepartement.getNomDepart());
    }

    @Test
    public void testRetrieveDepartement() {
        Departement existingDepartement = new Departement(5, "Dept5");


        when(departementRepository.findById(5)).thenReturn(Optional.of(existingDepartement));

        Departement retrievedDepartement = departementService.retrieveDepartement(5);


        assertNotNull(retrievedDepartement);
        assertEquals(5, retrievedDepartement.getIdDepart().intValue());
        assertEquals("Dept5", retrievedDepartement.getNomDepart());
    }

    @Test
    public void testDeleteDepartement() {
        Departement existingDepartement = new Departement(6, "Dept6");


        when(departementRepository.findById(6)).thenReturn(Optional.of(existingDepartement));

        departementService.deleteDepartement(6);


        verify(departementRepository).delete(existingDepartement);
    }
}
