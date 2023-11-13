package christmas.domain;

import christmas.exception.DateUnitException;

public class VisitDate {
    private final int date;
    private static final int DATE_MIN = 1;
    private static final int DATE_MAX = 31;

    public VisitDate(final int date) {
        validate(date);
        this.date = date;
    }

    private void validate(final int date) {
        if (date < DATE_MIN || date > DATE_MAX) {
            throw new DateUnitException();
        }
    }

    public int getDate() {
        return date;
    }
}
