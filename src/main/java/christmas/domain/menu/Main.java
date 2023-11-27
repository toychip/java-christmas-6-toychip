package christmas.domain.menu;

import static christmas.static_class.MenuStatic.BBQ_RIB_NAME;
import static christmas.static_class.MenuStatic.BBQ_RIB_PRICE;
import static christmas.static_class.MenuStatic.CHRISTMAS_PASTA_NAME;
import static christmas.static_class.MenuStatic.CHRISTMAS_PASTA_PRICE;
import static christmas.static_class.MenuStatic.SEAFOOD_PASTA_NAME;
import static christmas.static_class.MenuStatic.SEAFOOD_PASTA_PRICE;
import static christmas.static_class.MenuStatic.T_BONE_STEAK_NAME;
import static christmas.static_class.MenuStatic.T_BONE_STEAK_PRICE;

import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Main implements Menu {
    T_BONE_STEAK(new Name(T_BONE_STEAK_NAME), new Price(T_BONE_STEAK_PRICE)),
    BBQ_RIB(new Name(BBQ_RIB_NAME), new Price(BBQ_RIB_PRICE)),
    SEAFOOD_PASTA(new Name(SEAFOOD_PASTA_NAME), new Price(SEAFOOD_PASTA_PRICE)),
    CHRISTMAS_PASTA(new Name(CHRISTMAS_PASTA_NAME), new Price(CHRISTMAS_PASTA_PRICE));

    private final Name name;
    private final Price price;

    Main(final Name name, final Price price) {
        this.name = name;
        this.price = price;
    }


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
