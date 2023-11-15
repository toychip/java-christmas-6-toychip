package christmas.domain;

import static christmas.static_class.CommonStatic.NONE_NAME;

import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;

public record GiftMenuDto(Name menuName, Price quantity) {

    public static GiftMenuDto defaultValue() {
        Name defaultMenuName = new Name(NONE_NAME);
        Price defaultQuantity = new Price(0);
        return new GiftMenuDto(defaultMenuName, defaultQuantity);
    }
}
