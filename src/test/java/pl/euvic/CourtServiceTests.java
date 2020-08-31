package pl.euvic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.euvic.model.repositories.CourtRepository;
import pl.euvic.model.responses.CourtRestModel;
import pl.euvic.model.services.CourtService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class CourtServiceTests {

    @Autowired
    private CourtService courtService;

    @Autowired
    private CourtRepository courtRepository;

    @AfterEach
    void tearDown() {
        courtRepository.deleteAll();
    }

    @Test
    void addCourt() {
        final CourtRestModel court = new CourtRestModel("court");

        assertEquals(0, courtRepository.count());
        courtService.add(court);
        assertEquals(1, courtRepository.count());
    }

    @Test
    void deleteCourt() {
        final CourtRestModel court = new CourtRestModel("court");

        assertEquals(0, courtRepository.count());
        courtService.add(court);
        assertEquals(1, courtRepository.count());
        courtService.deleteById(2L);
        assertEquals(0, courtRepository.count());
    }
}
