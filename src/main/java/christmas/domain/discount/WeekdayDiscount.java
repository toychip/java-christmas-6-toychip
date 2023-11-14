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
        this.visitDate = visitDate;
        this.orders = orders;
        this.originalPrice = originalPrice;
        this.discountValue = discount();
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        int count = orderContainsDesertCount();
        return count * 2023;
    }

    private int orderContainsDesertCount() {
        List<String> desertMenuNames = getDesertMenuNames();
        return (int) orders.getOrders().stream()
                .map(Order::orderMenuName)
                .filter(desertMenuNames::contains)
                .count();
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
