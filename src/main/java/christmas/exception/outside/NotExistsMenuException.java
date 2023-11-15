package christmas.exception.outside;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

import christmas.exception.outside.order.OrderParentsException;

public class NotExistsMenuException extends OrderParentsException {
    public NotExistsMenuException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
