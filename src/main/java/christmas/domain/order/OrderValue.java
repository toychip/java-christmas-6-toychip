package christmas.domain.order;

public class OrderValue {
    private final int quantity;

    public OrderValue(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 주문 수량이 0이하일 수는 없습니다.");
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
