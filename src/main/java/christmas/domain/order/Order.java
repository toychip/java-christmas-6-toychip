package christmas.domain.order;

public record Order(OrderMenu orderMenu, OrderValue orderValue) {

    public String orderMenuName(){
     return orderMenu().menuName();
    }

    public int orderValueQuantity() {
        return orderValue.quantity();
    }
}
