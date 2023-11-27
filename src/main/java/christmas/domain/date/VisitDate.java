package christmas.domain.date;

import static christmas.static_class.DateStatic.DATE_MAX;
import static christmas.static_class.DateStatic.DATE_MIN;

import christmas.exception.outside.DateUnitException;

public class VisitDate {
    private final int date;

    public VisitDate(final String inputDate) {
        int date = toInt(inputDate);
        validate(date);
        this.date = date;
    }

    private int toInt(final String date) {
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
