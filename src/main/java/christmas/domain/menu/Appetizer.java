package christmas.domain.menu;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;

public enum Appetizer implements Menu {
    PINE_MUSHROOM_SOUP(new Name("양송이스프"), new Price(6000)),
    TAPAS(new Name("타파스"), new Price(5500)),
    CAESAR_SALAD(new Name("시저샐러드"), new Price(8000));

    private final Name name;
    private final Price price;

    Appetizer(Name name, Price price) {
        this.name = name;
        this.price = price;
    }


//    @Override
    public static List<Name> allName() {
        return Arrays.stream(Appetizer.values())
                .map(Appetizer::getName)
                .toList();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price.getValue();
    }
}