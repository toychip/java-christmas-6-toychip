package christmas.exception.inside.discount;

public class InvalidStarException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 별표있는 날이 아닌데, 특별 할인을 호출했습니다. (내부 오류)";

    public InvalidStarException() {
        super(MESSAGE);
    }
}
