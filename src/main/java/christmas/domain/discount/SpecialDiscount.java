package christmas.domain.discount;

import christmas.domain.date.VisitDate;
import christmas.domain.date.DecemberCalendar;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.InvalidStarException;

public class SpecialDiscount implements Discount{

    private final Price discountValue;

    public SpecialDiscount(VisitDate visitDate) {
        validate(visitDate);
        this.discountValue = discount();
    }

    private void validate(VisitDate visitDate) {
        validateIsStar(visitDate);
    }

    private void validateIsStar(VisitDate visitDate) {
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
        return 1000;
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }
}
