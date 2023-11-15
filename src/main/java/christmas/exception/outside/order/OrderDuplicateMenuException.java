package christmas.exception.outside.order;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class OrderDuplicateMenuException extends IllegalArgumentException {
    public OrderDuplicateMenuException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
