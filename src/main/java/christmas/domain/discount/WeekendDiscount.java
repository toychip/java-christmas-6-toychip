package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;

public class WeekendDiscount implements Discount{


    @Override
    public Price discount() {
        return null;
    }

    @Override
    public VisitDate getVisitDate() {
        return null;
    }

    @Override
    public Price getOriginalPrice() {
        return null;
    }

    @Override
    public Price getDiscountValue() {
        return null;
    }
}
