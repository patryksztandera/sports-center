package pl.euvic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.euvic.model.repositories.ReservationRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.responses.CourtRestModel;
import pl.euvic.model.responses.ReservationRestModel;
import pl.euvic.model.responses.ScheduleRestModel;
import pl.euvic.model.services.ClientService;
import pl.euvic.model.services.CourtService;
import pl.euvic.model.services.ReservationService;
import pl.euvic.model.services.ScheduleService;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class ReservationServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourtService courtService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void initializeDatabase() {
        final CourtRestModel court = new CourtRestModel("court");
        courtService.add(court);
        final ScheduleRestModel schedule = new ScheduleRestModel(ZonedDateTime.parse("2020-07-30T09:00:00+02:00"),
                ZonedDateTime.parse("2020-07-30T10:30:00+02:00"), 1L);
        scheduleService.add(schedule);
        final ClientRestModel client = new ClientRestModel("Name","Surname",
                "sport.centre.no@gmail.com","+48 123 456 789");
        clientService.add(client);
    }

    @Test
    void addReservation() {
        final ReservationRestModel reservation = new ReservationRestModel(2L,4L,5L);

        assertEquals(0, reservationRepository.count());
        reservationService.add(reservation);

        assertEquals(1, reservationRepository.count());
    }
}
