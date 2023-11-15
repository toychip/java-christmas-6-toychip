package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.date.DecemberCalendar;
import christmas.domain.menu.Desert;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.InvalidWeekdayException;
import java.util.List;

public class WeekdayDiscount implements Discount{

    private final Orders orders;
    private final Price discountValue;

    public WeekdayDiscount(VisitDate visitDate, Orders orders) {
        validate(visitDate);
        this.orders = orders;
        this.discountValue = discount();
    }

    private void validate(VisitDate visitDate) {
        validateIsWeekday(visitDate);
    }

    private void validateIsWeekday(VisitDate visitDate) {
        boolean isWeekDay = DecemberCalendar.matchWeekday(visitDate);
        if (!isWeekDay) {
            throw new InvalidWeekdayException();
        }
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
    public Price getDiscountValue() {
        return discountValue;
    }

    @Override
    public String toString() {
        return "WeekdayDiscount";
    }
}
