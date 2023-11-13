package christmas.domain.order;

public class Order {

    private final OrderMenu orderMenu;
    private final OrderValue orderValue;

    protected Order(OrderMenu orderMenu, OrderValue orderValue) {
        this.orderMenu = orderMenu;
        this.orderValue = orderValue;
    }

    public OrderMenu getOrderMenu() {
        return orderMenu;
    }

    public OrderValue getOrderValue() {
        return orderValue;
    }
}
