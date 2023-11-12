package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;

public enum Appetizer {
    PINE_MUSHROOM_SOUP(new Name("양송이스프"), new Price(6000)),
    TAPAS(new Name("타파스"), new Price(5500)),
    CAESAR_SALAD(new Name("시저샐러드"), new Price(8000));

    private final Name name;
    private final Price price;

    Appetizer(Name name, Price price) {
        this.name = name;
        this.price = price;
    }

    public List<Name> allName() {
        return Arrays.stream(Appetizer.values())
                .map(Appetizer::getName)
                .toList();
    }

    public Name getName() {
        return name;
    }
}
