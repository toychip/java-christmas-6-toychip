package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.discount.InvalidStarException;
import christmas.exception.discount.InvalidWeekdayException;

public class SpecialDiscount implements Discount{

    private final VisitDate visitDate;
    private final Price originalPrice;
    private final Price discountValue;

    public SpecialDiscount(VisitDate visitDate, Price originalPrice) {
        validate(visitDate);
        this.visitDate = visitDate;
        this.originalPrice = originalPrice;
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
