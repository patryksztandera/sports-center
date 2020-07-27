package pl.euvic;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.repositories.CourtRepository;
import pl.euvic.model.repositories.ScheduleRepository;
import pl.euvic.model.responses.ClientRestModel;
import pl.euvic.model.responses.CourtRestModel;
import pl.euvic.model.responses.ScheduleRestModel;
import pl.euvic.model.services.ClientService;
import pl.euvic.model.services.CourtService;
import pl.euvic.model.services.ScheduleService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2")
public class ScheduleServiceTests {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CourtService courtService;

    @Autowired
    private CourtRepository courtRepository;

    @BeforeEach
    void initializeDatabase() {
        final ClientRestModel client = new ClientRestModel("Name","Surname","name.surname@gmail.com","+48 123 456 789");
        final CourtRestModel court = new CourtRestModel("court");
        clientService.add(client);
        courtService.add(court);
    }

    @Test
    void addSchedule(){
        final ScheduleRestModel model = new ScheduleRestModel("2020-07-30T14:00:00+02:00","2020-07-30T14:30:00+02:00",1L,1L);

        assertEquals(0,scheduleRepository.count());

        scheduleService.add(model);
        assertEquals(1,scheduleRepository.count());
    }

}
