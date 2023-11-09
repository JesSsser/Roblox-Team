package tn.esprit.spring.kaddem.test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    private Equipe equipe1;
    private Equipe equipe2;

    @BeforeEach
    void init() {
        equipe1 = new Equipe( 1,"EQUIPE WED ELLIL", Niveau.EXPERT );
        equipe2 = new Equipe( 2,"ESPRIT", Niveau.JUNIOR );
    }

    @Test
    void retrieveAllEquipes() {
        List<Equipe> list = new ArrayList<>();
        list.add(equipe1);
        list.add(equipe2);

        when(equipeRepository.findAll()).thenReturn(list);

        List<Equipe> listEquipes = equipeService.retrieveAllEquipes();

        assertEquals(2, listEquipes.size());
        assertNotNull(listEquipes);

    }

    @Test
    void addEquipe() {
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe1);
        Equipe newEquipe = equipeService.addEquipe(equipe1);
        assertNotNull(newEquipe);
        assertThat(newEquipe.getNomEquipe()==("EQUIPE WED ELLIL"));

    }
}