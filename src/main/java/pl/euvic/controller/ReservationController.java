package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.ReservationRestModel;
import pl.euvic.model.services.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationRestModel>> listAllReservation() {
        final List<ReservationRestModel> reservation = reservationService.getAll();
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationRestModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addReservation(@RequestBody final ReservationRestModel model) {
        return ResponseEntity.ok(reservationService.add(model));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable final Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
