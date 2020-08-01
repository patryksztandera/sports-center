package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.ScheduleRestModel;
import pl.euvic.model.services.ClientService;
import pl.euvic.model.services.CourtService;
import pl.euvic.model.services.EmailService;
import pl.euvic.model.services.ScheduleService;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ClientService clientService;
    private final CourtService courtService;
    private final EmailService emailService;

    public ScheduleController(ScheduleService scheduleService,
                              ClientService clientService,
                              CourtService courtService,
                              EmailService emailService) {
        this.scheduleService = scheduleService;
        this.clientService = clientService;
        this.courtService = courtService;
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleRestModel>> listAllSchedule() {
        final List<ScheduleRestModel> schedule = scheduleService.getAll();

        return ResponseEntity.ok(schedule);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> addSchedule(@RequestBody final ScheduleRestModel model) {

        return ResponseEntity.ok(scheduleService.add(model));
    }
}
