package pl.euvic.model.responses;

import pl.euvic.model.entities.ScheduleEntity;

import java.time.ZonedDateTime;

public class ScheduleRestModel {

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;

    private Long clientId;

    private Long courtId;

    public ScheduleRestModel(ZonedDateTime startTime,
                             ZonedDateTime endTime,
                             Long clientId,
                             Long courtId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.courtId = courtId;
    }

    public ScheduleRestModel(){
    }

    public ScheduleRestModel(ScheduleEntity entity) {
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.clientId = entity.getClientEntity().getId();
        this.courtId = entity.getCourtEntity().getId();
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getCourtId() {
        return courtId;
    }
}
