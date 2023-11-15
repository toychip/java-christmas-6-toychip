package christmas.domain.discount;

import static christmas.static_class.DiscountStatic.WEEKDAY_DISCOUNT_NAME;
import static christmas.static_class.DiscountStatic.WEEK_DISCOUNT_UNIT;

import christmas.domain.date.DecemberCalendar;
import christmas.domain.date.VisitDate;
import christmas.domain.menu.Desert;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.InvalidWeekdayException;
import java.util.List;

public class WeekdayDiscount implements Discount {

    private final Orders orders;
    private final Price discountValue;

    public WeekdayDiscount(final VisitDate visitDate, final Orders orders) {
        validate(visitDate);
        this.orders = orders;
        discountValue = discount();
    }

    private void validate(final VisitDate visitDate) {
        validateIsWeekday(visitDate);
    }

    private void validateIsWeekday(final VisitDate visitDate) {
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
        return totalDesertQuantity * WEEK_DISCOUNT_UNIT;
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
        return WEEKDAY_DISCOUNT_NAME;
    }
}
