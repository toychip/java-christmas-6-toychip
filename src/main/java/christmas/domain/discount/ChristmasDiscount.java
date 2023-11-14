package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.XmasDiscountUnitException;

public class ChristmasDiscount implements Discount{

    private final VisitDate visitDate;
    private final Price discountValue;

    public ChristmasDiscount(VisitDate visitDate) {
        validate(visitDate);
        this.visitDate = visitDate;
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
    public Price getDiscountValue() {
        return discountValue;
    }
}
