package christmas.domain.discount;

import static christmas.static_class.DiscountStatic.WEEKEND_DISCOUNT_NAME;
import static christmas.static_class.DiscountStatic.WEEK_DISCOUNT_UNIT;

import christmas.domain.date.VisitDate;
import christmas.domain.date.DecemberCalendar;
import christmas.domain.menu.Main;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.InvalidWeekendException;
import java.util.List;

public class WeekendDiscount implements Discount{

    private final Orders orders;
    private final Price discountValue;

    public WeekendDiscount(final VisitDate visitDate, final Orders orders) {
        validate(visitDate);
        this.orders = orders;
        discountValue = discount();
    }

    private void validate(final VisitDate visitDate) {
        validateIsWeekend(visitDate);
    }

    private void validateIsWeekend(final VisitDate visitDate) {
        boolean isWeekend = DecemberCalendar.matchWeekend(visitDate);
        if (!isWeekend) {
            throw new InvalidWeekendException();
        }
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        int totalMainQuantity = getTotalMainQuantity();
        return totalMainQuantity * WEEK_DISCOUNT_UNIT;
    }

    private int getTotalMainQuantity() {
        List<String> mainMenuNames = getMainMenuNames();
        return orders.getOrders().stream()
                .filter(order -> mainMenuNames.contains(order.orderMenuName()))
                .mapToInt(Order::orderValueQuantity)
                .sum();
    }

    private List<String> getMainMenuNames() {
        return Main.allName().stream()
                .map(Name::toString)
                .toList();
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }

    @Override
    public String toString() {
        return WEEKEND_DISCOUNT_NAME;
    }
}
