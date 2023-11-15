package christmas.domain;

import christmas.domain.discount.GiftMenuEvent;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;

public class GiftMenuDto {
    private final Name menuName;
    private final Price quantity;

    public static GiftMenuDto defaultValue() {
        Name defaultMenuName = new Name("없음");
        Price defaultQuantity = new Price(0);
        return new GiftMenuDto(defaultMenuName, defaultQuantity);
    }

    public GiftMenuDto(Name menuName, Price quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public Name getMenuName() {
        return menuName;
    }

    public Price getQuantity() {
        return quantity;
    }
}
