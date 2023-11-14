package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.discount.XmasDiscountUnitException;

public class ChristmasDiscount implements Discount{

    private final VisitDate visitDate;
    private final Price originalPrice;
    private final Price discountValue;

    public ChristmasDiscount(VisitDate visitDate,
                             Price originalPrice) {
        validate(visitDate);
        this.visitDate = visitDate;
        this.originalPrice = originalPrice;
        discountValue = discount();
    }

    private void validate(VisitDate visitDate) {
        validateXmasPassed(visitDate);
    }

    private void validateXmasPassed(VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date > 25) {
            throw new XmasDiscountUnitException();
        }
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        int date = getDate();
        return date * 100 + 1000 - 100;
    }

    private int getDate() {
        return visitDate.getDate();
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
