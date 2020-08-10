package pl.euvic.exceptions;

import java.time.ZonedDateTime;

public class NotFoundException extends ApiException {

    public NotFoundException() {
        super(ZonedDateTime.now(), "Sorry, thing you looking for does not exist", 404,"Not found");
    }

}
