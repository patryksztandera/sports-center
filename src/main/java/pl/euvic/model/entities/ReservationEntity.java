package pl.euvic.model.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private ZonedDateTime startTime;

    @Column
    private ZonedDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public ReservationEntity(ZonedDateTime startTime, ZonedDateTime endTime, ClientEntity clientEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientEntity = clientEntity;
    }

    public ReservationEntity() {
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }
}
