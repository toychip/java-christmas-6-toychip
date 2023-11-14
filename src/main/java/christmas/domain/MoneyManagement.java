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


    public MoneyManagement(Orders orders, VisitDate visitDate, Price totalPrePrice, Name giftMenu,
                           List<Price> discountDetails, Price totalDiscountPrice, Price totalPostPrice, Name badge) {
        this.orders = orders;
        this.visitDate = visitDate;
        this.totalPrePrice = totalPrePrice;
        this.giftMenu = giftMenu;
        this.discountDetails = discountDetails;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalPostPrice = totalPostPrice;
        Badge = badge;
    }
}
