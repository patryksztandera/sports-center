package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.model.entities.ScheduleEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.repositories.CourtRepository;
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
    private final ClientRepository clientRepository;
    private final CourtRepository courtRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ClientRepository clientRepository,
                           CourtRepository courtRepository) {
        this.scheduleRepository = scheduleRepository;
        this.clientRepository = clientRepository;
        this.courtRepository = courtRepository;
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
        return new ScheduleEntity(ZonedDateTime.parse(model.getStartTime()),
                ZonedDateTime.parse(model.getEndTime()),
                clientRepository.getById(model.getClientId()),
                courtRepository.getById(model.getCourtId()));
    }
}
