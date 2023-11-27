package christmas.exception.outside.order;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class OrderValueMinUnitException extends OrderParentsException {
    public OrderValueMinUnitException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
