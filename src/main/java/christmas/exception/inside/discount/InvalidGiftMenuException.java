package christmas.exception.inside.discount;

public class InvalidGiftMenuException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 주문 금액이 120,000 이하인데, GiftMenu 생성을 시도했습니다. (내부 오류)";

    public InvalidGiftMenuException() {
        super(MESSAGE);
    }
}
