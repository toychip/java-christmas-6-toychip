package christmas.exception.outside.order;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class OrderTotalExceededException extends OrderParentsException {
    public OrderTotalExceededException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
