package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;

public enum Main {
    T_BONE_STEAK(new Name("티본스테이크"), new Price(55000)),
    BBQ_RIB(new Name("바비큐립"), new Price(54000)),
    SEAFOOD_PASTA(new Name("해산물파스타"), new Price(35000)),
    CHRISTMAS_PASTA(new Name("크리스마스파스타"), new Price(25000));

    private final Name name;
    private final Price price;

    Main(Name name, Price price) {
        this.name = name;
        this.price = price;
    }

    public List<Name> allName() {
        return Arrays.stream(Main.values())
                .map(Main::getName)
                .toList();
    }

    public Name getName() {
        return name;
    }
}
