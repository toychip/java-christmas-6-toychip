package christmas.exception.outside;

import static christmas.static_class.CommonStatic.INVALID_DATE_MESSAGE;

public class DateUnitException extends IllegalArgumentException {
    public DateUnitException() {
        super(INVALID_DATE_MESSAGE);
    }
}
