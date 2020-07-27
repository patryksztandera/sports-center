package pl.euvic.model.responses;

import pl.euvic.model.entities.ScheduleEntity;

import java.time.format.DateTimeFormatter;

public class ScheduleRestModel {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private String startTime;

    private String endTime;

    private Long clientId;

    private Long courtId;

    public ScheduleRestModel(String startTime, String endTime, Long clientId, Long courtId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.courtId = courtId;
    }

    public ScheduleRestModel(){
    }

    public ScheduleRestModel(ScheduleEntity entity) {
        this.startTime = entity.getStartTime().format(formatter);
        this.endTime = entity.getEndTime().format(formatter);
        this.clientId = entity.getClientEntity().getId();
        this.courtId = entity.getCourtEntity().getId();
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getCourtId() {
        return courtId;
    }
}
