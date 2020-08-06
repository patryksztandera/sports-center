package pl.euvic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.euvic.model.repositories.ScheduleRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.responses.CourtRestModel;
import pl.euvic.model.responses.ScheduleRestModel;
import pl.euvic.model.services.ClientService;
import pl.euvic.model.services.CourtService;
import pl.euvic.model.services.ScheduleService;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class ScheduleServiceTests {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourtService courtService;

    @BeforeEach
    void initializeDatabase() {
        final CourtRestModel court = new CourtRestModel("court");
        courtService.add(court);
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }

    @Test
    void addOneSlotInSchedule() {
        final ScheduleRestModel model = new ScheduleRestModel(ZonedDateTime.parse("2020-07-30T09:00:00+02:00"),
                ZonedDateTime.parse("2020-07-30T09:30:00+02:00"), 1L);

        assertEquals(0, scheduleRepository.count());

        scheduleService.add(model);
        assertEquals(1, scheduleRepository.count());
    }

    @Test
    void addSchedule() {
        final ScheduleRestModel model = new ScheduleRestModel(ZonedDateTime.parse("2020-07-30T09:00:00+02:00"),
                ZonedDateTime.parse("2020-07-30T14:30:00+02:00"), 1L);

        assertEquals(0, scheduleRepository.count());

        scheduleService.add(model);
        assertEquals(11, scheduleRepository.count());
    }

}
