package christmas.domain.order;

import christmas.exception.order.OrderValueMinUnitException;

public record OrderValue(int quantity) {
    public OrderValue {
        validate(quantity);
    }

    private void validate(final int quantity) {
        if (quantity <= 0) {
            throw new OrderValueMinUnitException();
        }
    }
}
