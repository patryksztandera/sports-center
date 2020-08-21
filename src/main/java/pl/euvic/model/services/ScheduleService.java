package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.exceptions.BadRequestException;
import pl.euvic.model.entities.ScheduleEntity;
import pl.euvic.model.repositories.CourtRepository;
import pl.euvic.model.repositories.ScheduleRepository;
import pl.euvic.model.responses.ScheduleRestModel;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CourtRepository courtRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           CourtRepository courtRepository) {
        this.scheduleRepository = scheduleRepository;
        this.courtRepository = courtRepository;
    }

    public List<ScheduleRestModel> getAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleRestModel::new)
                .collect(Collectors.toList());
    }

    public List<Long> add(ScheduleRestModel scheduleRestModel) {

        List<Long> schedule = new ArrayList<>();

        if (scheduleRestModel.getStartTime().getMinute() % 30 != 0
                || scheduleRestModel.getEndTime().getMinute() % 30 != 0) {
            throw new BadRequestException("Invalid time");
        } else {
            for (ZonedDateTime iterator = scheduleRestModel.getStartTime();
                 iterator.isBefore(scheduleRestModel.getEndTime());
                 iterator = iterator.plusMinutes(30L)) {

                if (courtRepository.existsById(scheduleRestModel.getCourtId())) {
                    ScheduleRestModel model = new ScheduleRestModel(
                            iterator,
                            iterator.plusMinutes(30),
                            scheduleRestModel.getCourtId());
                    schedule.add(scheduleRepository.save(mapRestModel(model)).getId());
                }
                else {
                    throw new BadRequestException("There is no such court");
                }
            }
        }
        return schedule;
    }

    public ScheduleEntity mapRestModel(ScheduleRestModel model) {
        return new ScheduleEntity(
                model.getStartTime(),
                model.getEndTime(),
                courtRepository.getById(model.getCourtId()));
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
