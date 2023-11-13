package christmas.domain.menu;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;

public enum Drink implements Menu {
    ZERO_COLA(new Name("제로콜라"), new Price(3000), false),
    RED_WINE(new Name("레드와인"), new Price(60000), false),
    CHAMPAGNE(new Name("샴페인"), new Price(25000), true);

    private final Name name;
    private final Price price;
    private final boolean giftOption;

    Drink(Name name, Price price, boolean giftOption) {
        this.name = name;
        this.price = price;
        this.giftOption = giftOption;
    }

    @Override
    public List<Name> allName() {
        return Arrays.stream(Drink.values())
                .map(Drink::getName)
                .toList();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    public boolean isGiftOption() {
        return giftOption;
    }

}
