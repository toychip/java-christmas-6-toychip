package christmas.domain.menu;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;

public enum Desert implements Menu {
    CHOCO_CAKE(new Name("초코케이크"), new Price(15000)),
    ICE_CREAM(new Name("아이스크림"), new Price(5000));

    private final Name name;
    private final Price price;

    Desert(final Name name, final Price price) {
        this.name = name;
        this.price = price;
    }

//    @Override
    public static List<Name> allName() {
        return Arrays.stream(Desert.values())
                .map(Desert::getName)
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
}
