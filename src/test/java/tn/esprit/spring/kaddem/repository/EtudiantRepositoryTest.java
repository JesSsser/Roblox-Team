package tn.esprit.spring.kaddem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
public class EtudiantRepositoryTest {
    @Autowired
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void init() {
        etudiant1 = new Etudiant( "Elouni", "Jesser" );
        etudiant2 = new Etudiant( "Test", "Test" );
    }

    @Test
    @DisplayName("It should save the student to the database")
    void save() {
        Etudiant newEtudiant = etudiantRepository.save(etudiant1);
        assertNotNull(newEtudiant);
        assertThat(newEtudiant.getIdEtudiant()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the students list with size of 2")
    void getEtudiants() {
        etudiantRepository.save(etudiant1);
        etudiantRepository.save(etudiant2);

            List<Etudiant> list = (List<Etudiant>) etudiantRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }
}
