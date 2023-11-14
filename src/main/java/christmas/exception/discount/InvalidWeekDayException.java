package christmas.exception.discount;

public class InvalidWeekDayException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 평일이 아닌데, 평일 할인을 호출했습니다. (내부 오류)";
    public InvalidWeekDayException() {
        super(MESSAGE);
    }
}
