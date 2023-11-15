package christmas.domain.discount;

import christmas.domain.menu.component.Price;

public interface Discount {
    Price discount();

    Price getDiscountValue();

}
