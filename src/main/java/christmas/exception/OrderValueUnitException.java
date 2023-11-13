package christmas.exception;

public class OrderValueUnitException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public OrderValueUnitException() {
        super(MESSAGE);
    }
}
