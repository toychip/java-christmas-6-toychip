package christmas.domain.discount;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;

public interface Discount {
    int discount();
    VisitDate getVisitDate();
    Price getOriginalPrice();
    Price getDiscountValue();

}
