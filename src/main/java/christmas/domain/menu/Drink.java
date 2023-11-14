package christmas.domain.menu;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Drink implements Menu {
    ZERO_COLA(new Name("제로콜라"), new Price(3000), false),
    RED_WINE(new Name("레드와인"), new Price(60000), false),
    CHAMPAGNE(new Name("샴페인"), new Price(25000), true);

    private final Name name;
    private final Price price;
    private final boolean giftOption;

    Drink(final Name name, final Price price, final boolean giftOption) {
        this.name = name;
        this.price = price;
        this.giftOption = giftOption;
    }

//    @Override
    public static List<Name> allName() {
        return Arrays.stream(Drink.values())
                .map(Drink::getName)
                .toList();
    }

    public static List<String> allNameString() {
        return Arrays.stream(Drink.values())
                .map(Drink::getName)
                .map(Name::getName)
                .toList();
    }

    public static Stream<Menu> streamAllDrink() {
        return Arrays.stream(Drink.values());
    }

    public static Drink generateGift() {
        return Drink.CHAMPAGNE;
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
