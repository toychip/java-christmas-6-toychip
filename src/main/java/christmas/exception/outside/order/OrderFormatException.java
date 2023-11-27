package christmas.exception.outside.order;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class OrderFormatException extends OrderParentsException {
    public OrderFormatException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
