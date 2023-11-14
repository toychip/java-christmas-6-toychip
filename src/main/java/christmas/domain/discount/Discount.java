package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;

public interface Discount {
    Price discount();
    VisitDate getVisitDate();
    Price getOriginalPrice();
    Price getDiscountValue();

}
