package christmas.exception.outside;

public class DateUnitException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public DateUnitException() {
        super(MESSAGE);
    }
}
