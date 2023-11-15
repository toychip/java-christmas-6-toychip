package christmas.domain.discount;

import static christmas.static_class.DiscountStatic.SPECIAL_DISCOUNT_NAME;
import static christmas.static_class.DiscountStatic.SPECIAL_DISCOUNT_VALUE;

import christmas.domain.date.DecemberCalendar;
import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.InvalidStarException;

public class SpecialDiscount implements Discount {

    private final Price discountValue;

    public SpecialDiscount(final VisitDate visitDate) {
        validate(visitDate);
        discountValue = discount();
    }

    private void validate(final VisitDate visitDate) {
        validateIsStar(visitDate);
    }

    private void validateIsStar(final VisitDate visitDate) {
        boolean isStar = DecemberCalendar.matchStar(visitDate);
        if (!isStar) {
            throw new InvalidStarException();
        }
    }

    @Override
    public Price discount() {
        int value = calculateDiscount();
        return new Price(value);
    }

    private int calculateDiscount() {
        return SPECIAL_DISCOUNT_VALUE;
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }

    @Override
    public String toString() {
        return SPECIAL_DISCOUNT_NAME;
    }
}
