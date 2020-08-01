package pl.euvic.model.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private CourtEntity courtEntity;

    public ScheduleEntity(ZonedDateTime startTime, ZonedDateTime endTime, CourtEntity courtEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courtEntity = courtEntity;
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

    public CourtEntity getCourtEntity() {
        return courtEntity;
    }
}
