package christmas.domain;

public class VisitDate {
    private final int date;
    private static final int DATE_MIN = 1;
    private static final int DATE_MAX = 31;

    public VisitDate(int date) {
        validate(date);
        this.date = date;
    }

    public void validate(int date) {
        if (date < DATE_MIN || date > DATE_MAX) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getDate() {
        return date;
    }
}
