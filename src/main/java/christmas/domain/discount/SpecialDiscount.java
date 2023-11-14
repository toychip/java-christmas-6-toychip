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
        this.discountValue = discount();
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

     private int calculateDiscount() {
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
