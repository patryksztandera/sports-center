package pl.euvic.model.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private ZonedDateTime startTime;

    @Column
    private ZonedDateTime endTime;

    @Column
    private Boolean reserved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private CourtEntity courtEntity;

    @OneToMany(mappedBy = "startReservation", cascade = CascadeType.ALL)
    private List<ReservationEntity> startReservation = new ArrayList<>();

    @OneToMany(mappedBy = "endReservation", cascade = CascadeType.ALL)
    private List<ReservationEntity> endReservation = new ArrayList<>();


    public ScheduleEntity(ZonedDateTime startTime, ZonedDateTime endTime, CourtEntity courtEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courtEntity = courtEntity;
        this.reserved = false;
    }

    public ScheduleEntity() {
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

    public Boolean getReserved() {
        return reserved;
    }

    public CourtEntity getCourtEntity() {
        return courtEntity;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
