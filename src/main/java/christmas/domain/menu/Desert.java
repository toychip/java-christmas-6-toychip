package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;

public enum Desert implements Menu{
    CHOCO_CAKE(new Name("초코케이크"), new Price(15000)),
    ICE_CREAM(new Name("아이스크림"), new Price(5000));

    private final Name name;
    private final Price price;

    Desert(Name name, Price price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public List<Name> allName() {
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
