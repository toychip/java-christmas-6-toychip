package christmas.domain.discount;

import static christmas.static_class.DiscountStatic.XMAS_DATE;
import static christmas.static_class.DiscountStatic.XMAS_DISCOUNT_NAME;
import static christmas.static_class.DiscountStatic.XMAS_MULTIPLE_UNIT;
import static christmas.static_class.DiscountStatic.XMAS_MinusUnit;
import static christmas.static_class.DiscountStatic.XMAS_PLUS_UNIT;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.XmasDiscountUnitException;

public class ChristmasDiscount implements Discount {

    private final VisitDate visitDate;
    private final Price discountValue;

    public ChristmasDiscount(final VisitDate visitDate) {
        validate(visitDate);
        this.visitDate = visitDate;
        discountValue = discount();
    }

    private void validate(final VisitDate visitDate) {
        validateXmasPassed(visitDate);
    }

    private void validateXmasPassed(final VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date > XMAS_DATE) {
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
        return date * XMAS_MULTIPLE_UNIT + XMAS_PLUS_UNIT - XMAS_MinusUnit;
    }

    private int getDate() {
        return visitDate.getDate();
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }

    @Override
    public String toString() {
        return XMAS_DISCOUNT_NAME;
    }
}
