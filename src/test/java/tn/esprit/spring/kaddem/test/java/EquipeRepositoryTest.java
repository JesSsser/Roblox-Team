package tn.esprit.spring.kaddem.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
class EquipeRepositoryTest {
    @Autowired
private EquipeRepository equipeRepository;

    private Equipe equipe1;
    private Equipe equipe2;

    @BeforeEach
    void init() {
        equipe1 = new Equipe( 1,"EQUIPE WED ELLIL", Niveau.EXPERT );
        equipe2 = new Equipe( 2,"ESPRIT", Niveau.JUNIOR );
    }

    @Test
    @DisplayName("enregistrer equipe")
    void save() {
        Equipe newEquipe = equipeRepository.save(equipe1);
        assertNotNull(newEquipe);
        assertThat(newEquipe.getIdEquipe()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("retourne 2")
    void getEquipes() {
        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);

        List<Equipe> listEquipe = (List<Equipe>) equipeRepository.findAll();

        assertNotNull(listEquipe);
        assertThat(listEquipe).isNotNull();
        assertEquals(2, listEquipe.size());
    }
}