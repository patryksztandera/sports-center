package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.CourtRestModel;
import pl.euvic.model.services.CourtService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("courts")
public class CourtController {

    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public ResponseEntity<List<CourtRestModel>> listAllCourts() {
        final List<CourtRestModel> courts = courtService.getAll();
        return ResponseEntity.ok(courts);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourtRestModel> getById(@PathVariable final Long id) {
        return ResponseEntity.ok(courtService.getById(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addCourt(@RequestBody final CourtRestModel court) {
        return ResponseEntity.ok(courtService.add(court));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        courtService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
