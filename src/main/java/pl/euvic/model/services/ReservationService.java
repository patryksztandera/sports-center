package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.exceptions.BadRequestException;
import pl.euvic.model.entities.ReservationEntity;
import pl.euvic.model.entities.ScheduleEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.repositories.ReservationRepository;
import pl.euvic.model.repositories.ScheduleRepository;
import pl.euvic.model.responses.ReservationRestModel;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;
    private final ClientRepository clientRepository;
    private final EmailService emailService;
    private final ClientService clientService;
    private final CourtService courtService;

    public ReservationService(ReservationRepository reservationRepository,
                              ScheduleRepository scheduleRepository,
                              ClientService clientService,
                              ClientRepository clientRepository,
                              EmailService emailService,
                              CourtService courtService) {
        this.reservationRepository = reservationRepository;
        this.scheduleRepository = scheduleRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
        this.courtService = courtService;
    }

    public List<ReservationRestModel> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationRestModel::new)
                .collect(Collectors.toList());
    }

    public ReservationRestModel getById(Long id) {
        return new ReservationRestModel(reservationRepository.getOne(id));
    }

    public Long add(ReservationRestModel reservationRestModel) {

        for (Long iterator = reservationRestModel.getStartScheduleId();
             iterator <= reservationRestModel.getEndScheduleId();
             iterator++) {

            ScheduleEntity scheduleEntity = scheduleRepository.getById(iterator);

            if (scheduleEntity.getReserved()) {
                throw new BadRequestException();
            }
        }
        Long id = reservationRepository.save(mapRestModel(reservationRestModel)).getId();

        ZonedDateTime startTime = scheduleRepository.getById(reservationRestModel.getStartScheduleId()).getStartTime();
        ZonedDateTime endTime = scheduleRepository.getById(reservationRestModel.getEndScheduleId()).getEndTime();

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy");

        emailService.sendMail(clientService.getById(reservationRestModel.getClientId()).getEmail(),
                "Confirmation from Sports Centre",
                "Hi " + clientService.getById(reservationRestModel.getClientId()).getName() +
                        ",\n\nWe confirm your reservation. Court \"" +
                        courtService.getById(scheduleRepository.getById(
                                reservationRestModel.getStartScheduleId())
                                .getCourtEntity().getId()).getName() + "\" for " +
                        ChronoUnit.MINUTES.between(startTime, endTime) + " min at " +
                        startTime.toLocalDateTime().format(formatter) +
                        ".\n\nSports Centre Team");

        for (Long iterator = reservationRestModel.getStartScheduleId();
             iterator <= reservationRestModel.getEndScheduleId();
             iterator++) {

            ScheduleEntity scheduleEntity = scheduleRepository.getById(iterator);
            scheduleEntity.setReserved(true);
            scheduleRepository.save(scheduleEntity);
        }
        return id;
    }

    public ReservationEntity mapRestModel(ReservationRestModel model) {
        return new ReservationEntity(
                scheduleRepository.getById(model.getStartScheduleId()),
                scheduleRepository.getById(model.getEndScheduleId()),
                clientRepository.getById(model.getClientId()));
    }

    public void deleteById(Long id) {
        ReservationEntity reservationEntity = reservationRepository.getOne(id);

        for (Long iterator = reservationEntity.getStartReservation().getId();
             iterator <= reservationEntity.getEndReservation().getId();
             iterator++) {

            ScheduleEntity scheduleEntity = scheduleRepository.getById(iterator);
            scheduleEntity.setReserved(false);
            scheduleRepository.save(scheduleEntity);
        }
        reservationRepository.deleteById(id);
    }
}
