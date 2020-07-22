package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.model.entities.ScheduleEntity;
import pl.euvic.model.repositories.ScheduleRepository;
import pl.euvic.model.responses.ScheduleRestModel;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleRestModel> getAll(){
        return scheduleRepository.findAll().stream()
                .map(ScheduleRestModel::new)
                .collect(Collectors.toList());
    }

    public Long add(ScheduleRestModel scheduleRestModel) {
                return scheduleRepository.save(mapRestModel(scheduleRestModel)).getId();
    }

    public ScheduleEntity mapRestModel(ScheduleRestModel model) {
        return new ScheduleEntity(model.getStartTime(),model.getEndTime());
    }

    public List<LocalDate> getDates() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(2);
ZonedDateTime time = ZonedDateTime.of(2002,5,20,13,30,0,0,UTC);
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }
}
