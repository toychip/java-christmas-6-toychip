package christmas.domain.order;

import java.util.Arrays;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Orders from(String userInput) {
        List<Order> unValidatedOrders = toOrders(userInput);
        List<Order> validatedOrders = validate(unValidatedOrders);
        return new Orders(validatedOrders);
    }

    private List<Order> validate(List<Order> unValidatedOrders) {
        // TODO 같은 메뉴가 중복되는지 확인

        return unValidatedOrders;
    }

    private List<Order> toOrders(String userOrders) {
        List<String> list = divideOrders(userOrders);
        return list.stream()
                .map(this::divideMenuAndValue)
                .toList();
    }

    // 티본스테이크-1,바비큐립-1,초코케이크-2 -> [티본스테이크-1] [바비큐립-1] [초코케이크-2]
    private List<String> divideOrders(String userInput) {
        return Arrays.stream(userInput.split(","))
                .map(String::trim)
                .toList();
    }

    private Order divideMenuAndValue(String orderInput) {
        List<String> parts = divideOrder(orderInput);
        String menuName = getMenuNameFromParts(parts);
        int quantity = getQuantityFromParts(parts);
        return new Order(new OrderMenu(menuName), new OrderValue(quantity));
    }

    // [티본스테이크-1] -> [티본스테이크], [1]
    private List<String> divideOrder(String userOrder) {
        return Arrays.stream(userOrder.split("-"))
                .map(String::trim)
                .toList();
    }

    private String getMenuNameFromParts(List<String> parts) {
        return parts.get(0); // 첫 번째 요소가 메뉴 이름
    }

    private int getQuantityFromParts(List<String> parts) {
        return Integer.parseInt(parts.get(1)); // 두 번째 요소가 수량
    }
}
