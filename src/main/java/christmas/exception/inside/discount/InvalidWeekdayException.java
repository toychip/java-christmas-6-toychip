package christmas.exception.inside.discount;

public class InvalidWeekdayException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 평일이 아닌데, 평일 할인을 호출했습니다. (내부 오류)";

    public InvalidWeekdayException() {
        super(MESSAGE);
    }
}
