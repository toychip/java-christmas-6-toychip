package christmas.domain;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Desert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.menu.component.Menu;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.NotExistsMenuInsideException;
import java.util.List;
import java.util.stream.Stream;

public class MoneyManagement {

    private final Orders orders;
    private final VisitDate visitDate;

    private final Price totalPrePrice;
    private final Name giftMenu;
    private final List<Price> discountDetails;
    private final Price totalDiscountPrice;
    private final Price totalPostPrice;
    private final Name Badge;


    public MoneyManagement(VisitDate visitDate, Orders orders) {
        this.orders = orders;
        this.visitDate = visitDate;
        this.totalPrePrice = getTotalPrePrice();
//        this.giftMenu = giftMenu;
//        this.discountDetails = discountDetails;
//        this.totalDiscountPrice = totalDiscountPrice;
//        this.totalPostPrice = totalPostPrice;
//        Badge = badge;
    }

    private Price getTotalPrePrice() {
        int totalPrice = orders.getOrders()
                .stream()
                .mapToInt(order -> {
                    String menuName = order.orderMenuName();
                    int quantity = order.orderValueQuantity();
                    int price = findPriceByMenuName(menuName);
                    return quantity * price;
                }).sum();

        return new Price(totalPrice);
    }

    private int findPriceByMenuName(String menuName) {
        List<Menu> allMenu = getAllMenu();
        return allMenu.stream()
                .filter(menu -> {
                    String inputMenu = menu.getName().getName();
                    return menuName.equals(inputMenu);
                })
                .findAny()
                .map(menu -> menu.getPrice().getValue())
                .orElseThrow(() -> NotExistsMenuInsideException.class);
    }

    private List<Menu> getAllMenu() {
        Stream<Menu> appetizerMenus = Appetizer.streamAllAppetizer();
        Stream<Menu> desertMenus = Desert.streamAllDesert();
        Stream<Menu> drinkMenus = Drink.streamAllDrink();
        Stream<Menu> mainMenus = Main.streamAllMain();

        return Stream.of(appetizerMenus, desertMenus, drinkMenus, mainMenus)
                .flatMap(stream -> stream)
                .toList();
    }



}
