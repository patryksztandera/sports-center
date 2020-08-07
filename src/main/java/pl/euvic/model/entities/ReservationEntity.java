package pl.euvic.model.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_id")
    private ScheduleEntity startReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_id")
    private ScheduleEntity endReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public ReservationEntity(ScheduleEntity startReservation, ScheduleEntity endReservation, ClientEntity clientEntity) {
        this.startReservation = startReservation;
        this.endReservation = endReservation;
        this.clientEntity = clientEntity;
    }

    public ReservationEntity() {
    }

    public Long getId() {
        return id;
    }

    public ScheduleEntity getStartReservation() {
        return startReservation;
    }

    public ScheduleEntity getEndReservation() {
        return endReservation;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }
}
