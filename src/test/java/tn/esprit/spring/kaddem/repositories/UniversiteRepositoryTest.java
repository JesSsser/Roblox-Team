package tn.esprit.spring.kaddem.repositories;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
class UniversiteRepositoryTest {
    @Autowired
    private UniversiteRepository universiteRepository;

    private Universite universite1;
    private Universite universite2;
    private Universite universite3;

    @BeforeEach
    void init() {
        universite1 = new Universite( 1, "Esprit" );
        universite2 = new Universite( 2, "TEKUP" );
        universite3 = new Universite( 3, "SESAME" );
    }

    @Test
    @DisplayName("University saved normally")
    void save() {
        Universite newUniversite = universiteRepository.save(universite1);
        assertNotNull(newUniversite);
        AssertionsForClassTypes.assertThat(newUniversite.getIdUniv()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("2 is the correct answer")
    void getUniversites() {
        universiteRepository.save(universite2);
        universiteRepository.save(universite3);

        List<Universite> list = (List<Universite>) universiteRepository.findAll();

        assertNotNull(list);
        AssertionsForClassTypes.assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }

}