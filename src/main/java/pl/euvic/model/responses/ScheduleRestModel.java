package pl.euvic.model.responses;

import pl.euvic.model.entities.ScheduleEntity;

import java.time.ZonedDateTime;

public class ScheduleRestModel {

    private ZonedDateTime startTime;

    private ZonedDateTime endTime;


    public ScheduleRestModel(ZonedDateTime startTime, ZonedDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ScheduleRestModel(){
    }

    public ScheduleRestModel(ScheduleEntity entity) {
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }
}
