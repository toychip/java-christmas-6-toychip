package christmas.exception.discount;

public class InvalidWeekendException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 주말이 아닌데, 주말 할인을 호출했습니다. (내부 오류)";
    public InvalidWeekendException() {
        super(MESSAGE);
    }
}
