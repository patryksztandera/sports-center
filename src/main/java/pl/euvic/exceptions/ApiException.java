package pl.euvic.exceptions;

import java.time.ZonedDateTime;

public class ApiException extends RuntimeException {

    private final Integer status;
    private final ZonedDateTime time;
    private final String error;

    protected ApiException(final ZonedDateTime time, final String message, final Integer status, final String error) {
        super(message);
        this.time = time;
        this.status = status;
        this.error = error;
    }

    public ApiException() {
        super("Internal Server Error");
        this.time = ZonedDateTime.now();
        this.status = 500;
        this.error = "Internal Server Error";
    }

    public Integer getStatus() {
        return status;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return getStatus() + " - " + getMessage();
    }
}
