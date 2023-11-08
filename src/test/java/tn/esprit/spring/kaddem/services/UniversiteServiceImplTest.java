package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    private Universite universite1;
    private Universite universite2;

    @BeforeEach
    void init() {
        universite1 = new Universite( 1, "Esprit");
        universite2 = new Universite( 2, "TEKUP" );
    }

    @Test
    void save() {
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite1);
        Universite newUniversite = universiteService.addUniversite(universite1);
        assertNotNull(newUniversite);
        assertThat(newUniversite.getNomUniv()).isEqualTo("Esprit");
    }

    @Test
    void getUniversites() {
        List<Universite> list = new ArrayList<>();
        list.add(universite1);
        list.add(universite2);

        when(universiteRepository.findAll()).thenReturn(list);

        List<Universite> universites = universiteService.retrieveAllUniversites();

        assertEquals(2, universites.size());
        assertNotNull(universites);
    }
}