package christmas.domain;

import christmas.exception.DateUnitException;

public class VisitDate {
    private final int date;
    private static final int DATE_MIN = 1;
    private static final int DATE_MAX = 31;

    public VisitDate(final String inputDate) {
        int date = toInt(inputDate);
        validate(date);
        this.date = date;
    }

    private int toInt(String date) {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new DateUnitException();
        }
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
