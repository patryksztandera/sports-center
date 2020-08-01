package pl.euvic.model.responses;

import pl.euvic.model.entities.ReservationEntity;

import java.time.format.DateTimeFormatter;

public class ReservationRestModel {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private String startTime;

    private String endTime;

    private Long scheduleId;

    private Long clientId;

    private Long courtId;

    public ReservationRestModel(Long scheduleId, Long clientId) {
        //this.startTime = startTime;
        //this.endTime = endTime;
        this.scheduleId = scheduleId;
        this.clientId = clientId;

    }

    public ReservationRestModel() {
    }

    public ReservationRestModel(ReservationEntity entity) {
        this.startTime = entity.getStartTime().format(formatter);
        this.endTime = entity.getEndTime().format(formatter);
        this.clientId = entity.getClientEntity().getId();
        this.courtId = entity.getCourtEntity().getId();
    }

    public Long getScheduleId() {
        return scheduleId;
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
