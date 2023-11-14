package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;

public class SpecialDiscount implements Discount{

    @Override
    public int discount() {
        return 0;
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
