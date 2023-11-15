package christmas.domain.menu;

import static christmas.static_class.MenuStatic.CAESAR_SALAD_NAME;
import static christmas.static_class.MenuStatic.CAESAR_SALAD_PRICE;
import static christmas.static_class.MenuStatic.PINE_MUSHROOM_SOUP_NAME;
import static christmas.static_class.MenuStatic.PINE_MUSHROOM_SOUP_PRICE;
import static christmas.static_class.MenuStatic.TAPAS_NAME;
import static christmas.static_class.MenuStatic.TAPAS_PRICE;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Appetizer implements Menu {
    PINE_MUSHROOM_SOUP(new Name(PINE_MUSHROOM_SOUP_NAME), new Price(PINE_MUSHROOM_SOUP_PRICE)),
    TAPAS(new Name(TAPAS_NAME), new Price(TAPAS_PRICE)),
    CAESAR_SALAD(new Name(CAESAR_SALAD_NAME), new Price(CAESAR_SALAD_PRICE));

    private final Name name;
    private final Price price;

    Appetizer(final Name name, final Price price) {
        this.name = name;
        this.price = price;
    }


    public static List<Name> allName() {
        return Arrays.stream(Appetizer.values())
                .map(Appetizer::getName)
                .toList();
    }

    public static Stream<Menu> streamAllAppetizer() {
        return Arrays.stream(Appetizer.values());
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
