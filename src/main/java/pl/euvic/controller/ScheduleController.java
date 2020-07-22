package pl.euvic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.euvic.model.responses.ScheduleRestModel;
import pl.euvic.model.services.ScheduleService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleRestModel>> listAllSchedule() {
        final List<ScheduleRestModel> schedule = scheduleService.getAll();

        return ResponseEntity.ok(schedule);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addSchedule(@RequestBody final ScheduleRestModel model) {
        if (model.getStartTime().getMinute() == 30 || model.getStartTime().getMinute() == 0
                && (model.getEndTime().getMinute() == 30 || model.getEndTime().getMinute() == 0)) {
            return ResponseEntity.ok(scheduleService.add(model));
        }
        return ResponseEntity.badRequest().build();
    }
}
