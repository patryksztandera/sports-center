package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.ReservationRestModel;
import pl.euvic.model.services.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ScheduleService scheduleService;
    private final ClientService clientService;
    private final CourtService courtService;
    private final EmailService emailService;


    public ReservationController(ReservationService reservationService,
                                 ScheduleService scheduleService,
                                 ClientService clientService,
                                 CourtService courtService,
                                 EmailService emailService) {
        this.reservationService = reservationService;
        this.scheduleService = scheduleService;
        this.clientService = clientService;
        this.courtService = courtService;
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationRestModel>> listAllReservation() {
        final List<ReservationRestModel> reservation = reservationService.getAll();

        return ResponseEntity.ok(reservation);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addReservation(@RequestBody final ReservationRestModel model) {
/*
        ZonedDateTime startTime = ZonedDateTime.parse(model.getStartTime());
        ZonedDateTime endTime = ZonedDateTime.parse(model.getEndTime());

        if (startTime.getMinute() % 30 == 0
                && endTime.getMinute() % 30 == 0) {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy");

            emailService.sendMail(clientService.getById(model.getClientId()).getEmail(),
                    "Confirmation from Sports Centre",
                    "Hi " + clientService.getById(model.getClientId()).getName() +
                            ",\n\nWe confirm your reservation. Court \"" +
                            courtService.getById(model.getCourtId()).getName() + "\" for " +
                            ChronoUnit.MINUTES.between(startTime, endTime) + " min at " +
                            startTime.toLocalDateTime().format(formatter) +
                            ".\n\nSports Centre Team");

            return ResponseEntity.ok(reservationService.add(model));
        }
        return ResponseEntity.badRequest().build();

 */
        return ResponseEntity.ok(reservationService.add(model));
    }
}
