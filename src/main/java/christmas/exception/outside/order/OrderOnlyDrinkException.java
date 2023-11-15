package christmas.exception.outside.order;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class OrderOnlyDrinkException extends IllegalArgumentException {

    public OrderOnlyDrinkException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
