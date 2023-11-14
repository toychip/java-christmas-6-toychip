package christmas.domain.order;

import christmas.domain.menu.Drink;
import christmas.exception.outside.order.OrderDuplicateMenuException;
import christmas.exception.outside.order.OrderOnlyDrinkException;
import christmas.exception.outside.order.OrderTotalExceededException;
import java.util.Arrays;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    public Orders(final String userInput) {
        List<Order> orders = toOrders(userInput);
        validate(orders);
        this.orders = orders;
    }

    private void validate(final List<Order> unValidatedOrders) {
        validateDuplicate(unValidatedOrders);
        validateOnlyDrink(unValidatedOrders);
        validateMaxValue(unValidatedOrders);
    }

    private void validateDuplicate(final List<Order> unValidatedOrders) {
        List<OrderMenu> orderMenus = getOrderMenus(unValidatedOrders);

        long count = orderMenus.stream()
                .distinct()
                .count();

        int size = orderMenus.size();

        if (count != size) {
            throw new OrderDuplicateMenuException();
        }
    }

    private List<OrderMenu> getOrderMenus(List<Order> unValidatedOrders) {
        return unValidatedOrders.stream()
                .map(Order::orderMenu)
                .toList();
    }

    private void validateOnlyDrink(final List<Order> unValidatedOrders) {
        boolean isOnlyDrink = isOnlyDrink(unValidatedOrders);
        if (isOnlyDrink) {
            throw new OrderOnlyDrinkException();
        }
    }

    private boolean isOnlyDrink(final List<Order> unValidatedOrders) {
        List<String> dinkNames = Drink.allNameString();
        return unValidatedOrders.stream()
                .allMatch(order -> dinkNames.contains(order.orderMenuName()));
    }

    private void validateMaxValue(List<Order> unValidatedOrders) {
        Long quantitySum = getQuantitySum(unValidatedOrders);
        if (quantitySum > 20) {
            throw new OrderTotalExceededException();
        }
    }

    private Long getQuantitySum(List<Order> unValidatedOrders) {
        return unValidatedOrders.stream()
                .mapToLong(Order::orderValueQuantity)
                .sum();
    }

    private List<Order> toOrders(final String userOrders) {
        List<String> list = divideOrders(userOrders);
        return list.stream()
                .map(this::divideMenuAndValue)
                .toList();
    }

    // 티본스테이크-1,바비큐립-1,초코케이크-2 -> [티본스테이크-1] [바비큐립-1] [초코케이크-2]
    private List<String> divideOrders(final String userInput) {
        return Arrays.stream(userInput.split(","))
                .map(String::trim)
                .toList();
    }

    private Order divideMenuAndValue(final String orderInput) {
        List<String> parts = divideOrder(orderInput);
        String menuName = getMenuNameFromParts(parts);
        int quantity = getQuantityFromParts(parts);
        return new Order(new OrderMenu(menuName), new OrderValue(quantity));
    }

    // [티본스테이크-1] -> [티본스테이크], [1]
    private List<String> divideOrder(final String userOrder) {
        return Arrays.stream(userOrder.split("-"))
                .map(String::trim)
                .toList();
    }

    private String getMenuNameFromParts(final List<String> parts) {
        return parts.get(0); // 첫 번째 요소가 메뉴 이름
    }

    private int getQuantityFromParts(final List<String> parts) {
        return Integer.parseInt(parts.get(1)); // 두 번째 요소가 수량
    }

    public List<Order> getOrders() {
        return orders;
    }
}
