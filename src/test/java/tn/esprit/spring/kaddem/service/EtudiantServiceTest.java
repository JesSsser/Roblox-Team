package tn.esprit.spring.kaddem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void init() {
        etudiant1 = new Etudiant( "Elouni", "Jesser" );
        etudiant2 = new Etudiant( "Test", "Test" );
    }

    @Test
    void save() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant1);
        Etudiant newEtudiant = etudiantService.addEtudiant(etudiant1);
        assertNotNull(newEtudiant);
        assertThat(newEtudiant.getNomE()).isEqualTo("Elouni");
    }

    @Test
    void getEtudiants() {
        List<Etudiant> list = new ArrayList<>();
        list.add(etudiant1);
        list.add(etudiant2);

        when(etudiantRepository.findAll()).thenReturn(list);

        List<Etudiant> movies = etudiantService.retrieveAllEtudiants();

        assertEquals(2, movies.size());
        assertNotNull(movies);
    }
}
