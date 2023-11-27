package christmas.exception.inside;

public class PriceUnitException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 금액이 0원 이하로 생성될 수 없습니다. (내부 오류)";

    public PriceUnitException() {
        super(MESSAGE);
    }
}
