package pl.euvic.model.responses;

import pl.euvic.model.entities.ScheduleEntity;

import java.time.ZonedDateTime;

public class ScheduleRestModel {

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Boolean reserved;

    private Long courtId;

    public ScheduleRestModel(ZonedDateTime startTime, ZonedDateTime endTime, Long courtId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.courtId = courtId;
    }

    public ScheduleRestModel(){
    }

    public ScheduleRestModel(ScheduleEntity entity) {
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.courtId = entity.getCourtEntity().getId();
        this.reserved = entity.getReserved();
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

    public Long getCourtId() {
        return courtId;
    }
}
