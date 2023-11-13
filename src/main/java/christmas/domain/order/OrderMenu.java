package christmas.domain.order;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Desert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.component.Name;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public record OrderMenu(String menuName) {
    public OrderMenu {
        validate(menuName);
    }

    private void validate(String userMenu) {
        validateMenuExistence(userMenu);
    }

    private void validateMenuExistence(String userMenu) {
        List<Name> allMenuNames = getAllMenuNames();
        boolean isContains = allMenuNames.stream()
                .anyMatch(name -> name.toString().equals(userMenu));

        if (!isContains) {
            throw new IllegalArgumentException("[ERROR] 메뉴에 없는 주문");
        }
    }

    private List<Name> getAllMenuNames() {
        return Stream.of(
                        Appetizer.allName(),
                        Desert.allName(),
                        Drink.allName(),
                        Main.allName()
                ).flatMap(Collection::stream)
                .toList();
    }
}
