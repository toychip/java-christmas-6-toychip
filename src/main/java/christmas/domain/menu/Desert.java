package christmas.domain.menu;

import static christmas.static_class.MenuStatic.CHOCO_CAKE_NAME;
import static christmas.static_class.MenuStatic.CHOCO_CAKE_PRICE;
import static christmas.static_class.MenuStatic.ICE_CREAM_NAME;
import static christmas.static_class.MenuStatic.ICE_CREAM_PRICE;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Desert implements Menu {
    CHOCO_CAKE(new Name(CHOCO_CAKE_NAME), new Price(CHOCO_CAKE_PRICE)),
    ICE_CREAM(new Name(ICE_CREAM_NAME), new Price(ICE_CREAM_PRICE));

    private final Name name;
    private final Price price;

    Desert(final Name name, final Price price) {
        this.name = name;
        this.price = price;
    }

    public static List<Name> allName() {
        return Arrays.stream(Desert.values())
                .map(Desert::getName)
                .toList();
    }

    public static Stream<Menu> streamAllDesert() {
        return Arrays.stream(Desert.values());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Price getPrice() {
        return price;
    }
}
