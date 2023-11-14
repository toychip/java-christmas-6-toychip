package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.Desert;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.List;

public class WeekdayDiscount implements Discount{

    private final VisitDate visitDate;
    private final Orders orders;
    private final Price originalPrice;
    private final Price discountValue;

    public WeekdayDiscount(VisitDate visitDate, Orders orders, Price originalPrice) {
        validate(visitDate);
        this.visitDate = visitDate;
        this.orders = orders;
        this.originalPrice = originalPrice;
        this.discountValue = discount();
    }

    private void validate(VisitDate visitDate) {
        // TODO 날짜가 평일이 맞는지 검증
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        int totalDesertQuantity = getTotalDesertQuantity();
        return totalDesertQuantity * 2023;
    }

    private int getTotalDesertQuantity() {
        List<String> desertMenuNames = getDesertMenuNames();
        return orders.getOrders().stream()
                .filter(order -> desertMenuNames.contains(order.orderMenuName()))
                .mapToInt(Order::orderValueQuantity)
                .sum();
    }

    private List<String> getDesertMenuNames() {
        return Desert.allName().stream()
                .map(Name::toString)
                .toList();
    }

    @Override
    public VisitDate getVisitDate() {
        return visitDate;
    }

    @Override
    public Price getOriginalPrice() {
        return originalPrice;
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }
}
