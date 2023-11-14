package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.Main;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.exception.discount.InvalidWeekDayException;
import java.util.List;

public class WeekendDiscount implements Discount{

    private final VisitDate visitDate;
    private final Orders orders;
    private final Price originalPrice;
    private final Price discountValue;

    public WeekendDiscount(VisitDate visitDate, Orders orders, Price originalPrice) {
        validate(visitDate);
        this.visitDate = visitDate;
        this.orders = orders;
        this.originalPrice = originalPrice;
        this.discountValue = discount();
    }

    private void validate(VisitDate visitDate) {
        validateIsWeekend(visitDate);
    }

    private void validateIsWeekend(VisitDate visitDate) {
        boolean isWeekend = DecemberCalendar.matchWeekend(visitDate);
        if (!isWeekend) {
            throw new InvalidWeekDayException();
        }
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        int totalMainQuantity = getTotalMainQuantity();
        return totalMainQuantity * 2023;
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
