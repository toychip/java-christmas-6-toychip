package christmas.domain;

import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;

public record DiscountDetailDto(Name discountName, Price discountPrice) {
}
