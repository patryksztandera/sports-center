package pl.euvic.model.responses;

import pl.euvic.model.entities.CourtEntity;

public class CourtRestModel {

    private String name;

    public CourtRestModel(final String name){
        this.name = name;
    }

    public CourtRestModel(){
    }

    public CourtRestModel(CourtEntity entity) {
        this.name = entity.getName();
    }

    public String getName() {
        return name;
    }
}
