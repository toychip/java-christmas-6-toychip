package christmas.exception.order;

public class OrderTotalExceededException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public OrderTotalExceededException() {
        super(MESSAGE);
    }
}
