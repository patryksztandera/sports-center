package pl.euvic.exceptions;

import java.time.ZonedDateTime;

public class BadRequestException extends ApiException{

    public BadRequestException() {
        super(ZonedDateTime.now(),"Send correct data, please",400,"Bad request");
    }
}
