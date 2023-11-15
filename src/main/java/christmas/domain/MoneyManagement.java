package christmas.domain;

import static christmas.domain.DiscountDto.judgeGiftMenuEvent;
import static christmas.domain.DiscountDto.judgeSpecialDiscount;
import static christmas.domain.DiscountDto.judgeWeekdayDiscount;
import static christmas.domain.DiscountDto.judgeWeekendDiscount;
import static christmas.domain.DiscountDto.judgeXmasDiscount;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.GiftMenuEvent;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
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
    private final GiftMenuEvent giftMenu;
    private final List<Price> discountDetails;
    private final Price totalDiscountPrice;
    private final Price totalPostPrice;
    private final Name Badge;


    public MoneyManagement(VisitDate visitDate, Orders orders) {
        this.orders = orders;
        this.visitDate = visitDate;
        this.totalPrePrice = getTotalPrePrice();
        this.giftMenu = canGiftMenu();
        this.discountDetails = getDiscountDetails();
        this.totalDiscountPrice = getTotalDiscountPrice();
        this.totalPostPrice = getTotalPostPrice();
        Badge = getBadge();
    }

    private static Price notDiscount() {
        return new Price(0);
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
                .orElseThrow(NotExistsMenuInsideException::new);
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

    private GiftMenuEvent canGiftMenu() {
        Price totalPrePrice = getTotalPrePrice();
        return new GiftMenuEvent(totalPrePrice);
    }

    private DiscountDto getDiscountDto() {
        ChristmasDiscount christmasDiscount = judgeXmasDiscount(visitDate);
        GiftMenuEvent giftMenuEvent = judgeGiftMenuEvent(totalPrePrice);
        SpecialDiscount specialDiscount = judgeSpecialDiscount(visitDate);
        WeekdayDiscount weekdayDiscount = judgeWeekdayDiscount(visitDate, orders);
        WeekendDiscount weekendDiscount = judgeWeekendDiscount(visitDate, orders);

        return new DiscountDto(christmasDiscount, giftMenuEvent,
                specialDiscount, weekdayDiscount,
                weekendDiscount);
    }

    private Price getTotalDiscountPrice() {
        DiscountDto discountDto = getDiscountDto();
        Price xmasDiscount = getXmasDiscount(discountDto);
        Price giftMenuEvent = getGiftMenuEvent(discountDto);
        Price specialDiscount = getSpecialDiscount(discountDto);
        Price weekdayDiscount = getWeekdayDiscount(discountDto);
        Price weekendDiscount = getWeekendDiscount(discountDto);

        int totalDiscountPrice = Stream.of(xmasDiscount, giftMenuEvent, specialDiscount, weekdayDiscount, weekendDiscount)
                .mapToInt(Price::getValue)
                .sum();

        return new Price(totalDiscountPrice);
    }

    private Price getXmasDiscount(DiscountDto discountDto) {
        if (judgeXmasDiscount(visitDate) != null) {
            ChristmasDiscount christmasDiscount = discountDto.getChristmasDiscount();
            return christmasDiscount.discount();
        }
        return notDiscount();
    }

    private Price getGiftMenuEvent(DiscountDto discountDto) {
        Price orderPrice = getTotalPrePrice();
        if (judgeGiftMenuEvent(orderPrice) != null) {
            GiftMenuEvent giftMenuEvent = discountDto.getGiftMenuEvent();
            return giftMenuEvent.discount();
        }
        return notDiscount();
    }

    private Price getSpecialDiscount(DiscountDto discountDto) {
        if (judgeSpecialDiscount(visitDate) != null) {
            SpecialDiscount specialDiscount = discountDto.getSpecialDiscount();
            return specialDiscount.discount();
        }
        return notDiscount();
    }

    private Price getWeekdayDiscount(DiscountDto discountDto) {
        if (judgeWeekdayDiscount(visitDate, orders) != null) {
            WeekdayDiscount weekdayDiscount = discountDto.getWeekdayDiscount();
            return weekdayDiscount.discount();
        }
        return notDiscount();
    }

    private Price getWeekendDiscount(DiscountDto discountDto) {
        if (judgeWeekendDiscount(visitDate, orders) != null) {
            WeekendDiscount weekendDiscount = discountDto.getWeekendDiscount();
            return weekendDiscount.discount();
        }
        return notDiscount();
    }

    private List<Price> getDiscountDetails() {
        return null;
    }

    private Price getTotalPostPrice() {
        return null;
    }

    private Name getBadge() {
        return null;
    }
}
