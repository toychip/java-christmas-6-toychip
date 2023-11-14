package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;

public class SpecialDiscount implements Discount{

    private final VisitDate visitDate;
    private final Price originalPrice;
    private final Price discountValue;

    public SpecialDiscount(VisitDate visitDate, Price originalPrice) {
        this.visitDate = visitDate;
        this.originalPrice = originalPrice;
        this.discountValue = calculateDiscount();
    }

    private Price calculateDiscount() {
        int value = discount();
        return new Price(value);
    }

    @Override
    public int discount() {
        return 1000;
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
