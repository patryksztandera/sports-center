package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.exceptions.BadRequestException;
import pl.euvic.exceptions.NotFoundException;
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

    public ReservationService(ReservationRepository reservationRepository,
                              ScheduleRepository scheduleRepository,
                              ClientRepository clientRepository,
                              EmailService emailService) {
        this.reservationRepository = reservationRepository;
        this.scheduleRepository = scheduleRepository;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    public List<ReservationRestModel> getAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationRestModel::new)
                .collect(Collectors.toList());
    }

    public ReservationRestModel getById(Long id) {
        List<Long> list = reservationRepository.findAll().stream()
                .map(ReservationEntity::getId)
                .collect(Collectors.toList());
        if (!list.contains(id)) {
            throw new NotFoundException("Such reservation does not exist!");
        }
        return new ReservationRestModel(reservationRepository.getOne(id));
    }

    public Long add(ReservationRestModel reservationRestModel) {

        if (scheduleRepository.existsById(reservationRestModel.getStartScheduleId())
                && scheduleRepository.existsById(reservationRestModel.getEndScheduleId())) {
            for (Long iterator = reservationRestModel.getStartScheduleId();
                 iterator <= reservationRestModel.getEndScheduleId();
                 iterator++) {

                ScheduleEntity scheduleEntity = scheduleRepository.getById(iterator);
                if (scheduleEntity.getReserved()) {
                    throw new BadRequestException("Reserved events were chosen");
                } else {
                    scheduleEntity.setReserved(true);
                    scheduleRepository.save(scheduleEntity);
                }
            }
        } else {
            throw new BadRequestException("Out of range");
        }

        Long id = reservationRepository.save(mapRestModel(reservationRestModel)).getId();

        final ZonedDateTime startTime = reservationRepository.getOne(id).getStartReservation().getStartTime();
        final ZonedDateTime endTime = reservationRepository.getOne(id).getEndReservation().getEndTime();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy");

        emailService.sendMail(
                reservationRepository.getOne(id).getClientEntity().getEmail(),
                "Confirmation from Sports Centre",
                "Hi " +
                        reservationRepository.getOne(id).getClientEntity().getName()
                        + ",\n\nWe confirm your reservation: court \"" +
                        reservationRepository.getOne(id).getStartReservation().getCourtEntity().getName()
                        + "\" for " +
                        ChronoUnit.MINUTES.between(startTime, endTime)
                        + " min at " +
                        startTime.toLocalDateTime().format(formatter) + ".\n\nSports Centre Team");
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
