package christmas.exception.outside;

import static christmas.static_class.CommonStatic.INVALID_ORDER_MESSAGE;

public class NotExistsMenuException extends IllegalArgumentException {
    public NotExistsMenuException() {
        super(INVALID_ORDER_MESSAGE);
    }
}
