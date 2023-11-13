package christmas.domain.order;

public record OrderValue(int quantity) {
    public OrderValue {
        validate(quantity);
    }

    private void validate(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 주문 수량이 0이하일 수는 없습니다.");
        }
    }
}
