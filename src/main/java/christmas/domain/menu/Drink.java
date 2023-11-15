package christmas.domain.menu;

import static christmas.static_class.MenuStatic.CHAMPAGNE_NAME;
import static christmas.static_class.MenuStatic.CHAMPAGNE_PRICE;
import static christmas.static_class.MenuStatic.RED_WINE_NAME;
import static christmas.static_class.MenuStatic.RED_WINE_PRICE;
import static christmas.static_class.MenuStatic.ZERO_COLA_NAME;
import static christmas.static_class.MenuStatic.ZERO_COLA_PRICE;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Drink implements Menu {
    ZERO_COLA(new Name(ZERO_COLA_NAME), new Price(ZERO_COLA_PRICE), false),
    RED_WINE(new Name(RED_WINE_NAME), new Price(RED_WINE_PRICE), false),
    CHAMPAGNE(new Name(CHAMPAGNE_NAME), new Price(CHAMPAGNE_PRICE), true);

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
