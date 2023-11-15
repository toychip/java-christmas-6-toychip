package christmas.domain;

import static christmas.domain.DiscountDto.judgeGiftMenuEvent;
import static christmas.domain.DiscountDto.judgeSpecialDiscount;
import static christmas.domain.DiscountDto.judgeWeekdayDiscount;
import static christmas.domain.DiscountDto.judgeWeekendDiscount;
import static christmas.domain.DiscountDto.judgeXmasDiscount;

import christmas.domain.date.VisitDate;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.EventBadge;
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
    private final Name giftName;
    private final List<Price> discountDetails;
    private final Price totalDiscountPrice;
    private final Price totalPostPrice;
    private final Name Badge;

    public MoneyManagement(VisitDate visitDate, Orders orders) {
        this.orders = orders;
        this.visitDate = visitDate;
        totalPrePrice = initTotalPrePrice();
        giftName = initGiftName();
        discountDetails = initDiscountDetails();
        totalDiscountPrice = initTotalDiscountPrice();
        totalPostPrice = initTotalPostPrice();
        Badge = initBadge();
    }

    private Price initTotalPrePrice() {
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

    private Name initGiftName() {
        DiscountDto discountDto = getDiscountDto();
        GiftMenuEvent giftMenuEvent = getGiftMenuEvent(discountDto);
        if (giftMenuEvent != null) {
            String giftName = giftMenuEvent.getGiftName();
            return new Name(giftName);
        }
        String nothing = "없음";
        return new Name(nothing);
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

    private List<Price> initDiscountDetails() {
        DiscountDto discountDto = getDiscountDto();
        Price xmasDiscount = getXmasDiscount(discountDto);
        Price giftPrice = getGiftPrice(discountDto);
        Price specialDiscount = getSpecialDiscount(discountDto);
        Price weekdayDiscount = getWeekdayDiscount(discountDto);
        Price weekendDiscount = getWeekendDiscount(discountDto);

        return Stream.of(xmasDiscount, giftPrice, specialDiscount, weekdayDiscount, weekendDiscount)
                .toList();
    }

    private Price getXmasDiscount(DiscountDto discountDto) {
        if (judgeXmasDiscount(visitDate) != null) {
            ChristmasDiscount christmasDiscount = discountDto.getChristmasDiscount();
            return christmasDiscount.discount();
        }
        return notDiscount();
    }

    private Price notDiscount() {
        return new Price(0);
    }

    private GiftMenuEvent getGiftMenuEvent(DiscountDto discountDto) {
        Price orderPrice = initTotalPrePrice();
        if (judgeGiftMenuEvent(orderPrice) != null) {
            return discountDto.getGiftMenuEvent();
        }
        return null;
    }

    private Price getGiftPrice(DiscountDto discountDto) {
        GiftMenuEvent giftMenu = getGiftMenuEvent(discountDto);
        if (giftMenu != null) {
            return giftMenu.getDiscountValue();
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

    private Price initTotalDiscountPrice() {
        List<Price> discountDetails = initDiscountDetails();
        int totalDiscountPrice = discountDetails.stream()
                .mapToInt(Price::getValue)
                .sum();

        return new Price(totalDiscountPrice);
    }

    private Price initTotalPostPrice() {
        int postValue = totalPrePrice.getValue() - totalDiscountPrice.getValue();

        DiscountDto discountDto = getDiscountDto();
        GiftMenuEvent giftMenuEvent = getGiftMenuEvent(discountDto);
        if (giftMenuEvent != null) {
            postValue = postValue + 25000;
        }

        return new Price(postValue);
    }

    private Name initBadge() {
        EventBadge eventBadge = EventBadge.findByPrice(totalDiscountPrice);
        return eventBadge.getBadgeName();
    }

    public Price getTotalPrePrice() {
        return totalPrePrice;
    }

    public Name getGiftName() {
        return giftName;
    }

    public List<Price> getDiscountDetails() {
        return discountDetails;
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Price getTotalPostPrice() {
        return totalPostPrice;
    }

    public Name getBadge() {
        return Badge;
    }
}
