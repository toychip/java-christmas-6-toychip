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
        discountValue = calculateDiscount();
    }

    private void validate(VisitDate visitDate) {
        int date = visitDate.getDate();
        if (date > 25) {
            throw new XmasDiscountUnitException();
        }
    }

    private Price calculateDiscount() {
        int value = discount();
        return new Price(value);
    }

    @Override
    public int discount() {
        int date = getDate();
        return date * 100 + 1000 - 100;
    }

    public int getDate() {
        return visitDate.getDate();
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
