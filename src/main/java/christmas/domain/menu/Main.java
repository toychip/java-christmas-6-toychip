package christmas.domain.menu;

import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Main implements Menu {
    T_BONE_STEAK(new Name("티본스테이크"), new Price(55000)),
    BBQ_RIB(new Name("바비큐립"), new Price(54000)),
    SEAFOOD_PASTA(new Name("해산물파스타"), new Price(35000)),
    CHRISTMAS_PASTA(new Name("크리스마스파스타"), new Price(25000));

    private final Name name;
    private final Price price;

    Main(final Name name, final Price price) {
        this.name = name;
        this.price = price;
    }

//    @Override
    public static List<Name> allName() {
        return Arrays.stream(Main.values())
                .map(Main::getName)
                .toList();
    }

    public static Stream<Menu> streamAllMain() {
        return Arrays.stream(Main.values());
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
