package pl.euvic.model.responses;

import pl.euvic.model.entities.ReservationEntity;

import java.time.format.DateTimeFormatter;

public class ReservationRestModel {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    //private String startTime;

    //private String endTime;

    private Long startScheduleId;

    private Long endScheduleId;

    private Long clientId;

    public ReservationRestModel(Long startScheduleId,Long endScheduleId, Long clientId) {
        //this.startTime = startTime;
        //this.endTime = endTime;
        this.startScheduleId = startScheduleId;
        this.endScheduleId = endScheduleId;
        this.clientId = clientId;

    }

    public ReservationRestModel() {
    }

    public ReservationRestModel(ReservationEntity entity) {
        //this.startTime = entity.getStartTime().format(formatter);
        //this.endTime = entity.getEndTime().format(formatter);
        this.clientId = entity.getClientEntity().getId();
    }

    public Long getStartScheduleId() {
        return startScheduleId;
    }

    public Long getEndScheduleId() {
        return endScheduleId;
    }

    public Long getClientId() {
        return clientId;
    }
}
