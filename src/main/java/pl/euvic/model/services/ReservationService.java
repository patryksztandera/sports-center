package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.model.entities.ReservationEntity;
import pl.euvic.model.entities.ScheduleEntity;
import pl.euvic.model.repositories.ClientRepository;
import pl.euvic.model.repositories.CourtRepository;
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

    public List<ReservationRestModel> getAll(){
        return reservationRepository.findAll().stream()
                .map(ReservationRestModel::new)
                .collect(Collectors.toList());
    }

    public Long add(ReservationRestModel reservationRestModel) {

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

        return id;
    }

    public ReservationEntity mapRestModel(ReservationRestModel model) {
        return new ReservationEntity(
                scheduleRepository.getById(model.getStartScheduleId()).getStartTime(),
                scheduleRepository.getById(model.getEndScheduleId()).getEndTime(),
                clientRepository.getById(model.getClientId()));
    }
}
