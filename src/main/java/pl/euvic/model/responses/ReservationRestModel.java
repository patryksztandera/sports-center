package pl.euvic.model.responses;

import pl.euvic.model.entities.ReservationEntity;

public class ReservationRestModel {

    private Long startScheduleId;

    private Long endScheduleId;

    private Long clientId;

    public ReservationRestModel(Long startScheduleId, Long endScheduleId, Long clientId) {
        this.startScheduleId = startScheduleId;
        this.endScheduleId = endScheduleId;
        this.clientId = clientId;
    }

    public ReservationRestModel() {
    }

    public ReservationRestModel(ReservationEntity entity) {
        this.startScheduleId = entity.getStartReservation().getId();
        this.endScheduleId = entity.getEndReservation().getId();
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
