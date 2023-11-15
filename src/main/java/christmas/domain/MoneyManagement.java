package christmas.domain;

import static christmas.domain.DiscountDto.judgeGiftMenuEvent;
import static christmas.domain.DiscountDto.judgeSpecialDiscount;
import static christmas.domain.DiscountDto.judgeWeekdayDiscount;
import static christmas.domain.DiscountDto.judgeWeekendDiscount;
import static christmas.domain.DiscountDto.judgeXmasDiscount;
import static christmas.static_class.DiscountStatic.GIFT_MENU_AMOUNT;

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
import java.util.Objects;
import java.util.stream.Stream;

public class MoneyManagement {

    private final Orders orders;
    private final VisitDate visitDate;
    private final Price totalPrePrice;
    private final GiftMenuDto giftMenu;
    private final List<DiscountDetailDto> discountDetails;
    private final Price totalDiscountPrice;
    private final Price totalPostPrice;
    private final EventBadge Badge;

    public MoneyManagement(VisitDate visitDate, Orders orders) {
        this.orders = orders;
        this.visitDate = visitDate;
        totalPrePrice = initTotalPrePrice();
        giftMenu = initGiftName();
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
                    String inputMenu = menu.getName().name();
                    return menuName.equals(inputMenu);
                })
                .findAny()
                .map(menu -> menu.getPrice().value())
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

    private GiftMenuDto initGiftName() {
        DiscountDto discountDto = getDiscountDto();
        GiftMenuEvent giftMenuEvent = getGiftMenuEvent(discountDto);
        if (giftMenuEvent != null) {
            Name giftName = giftMenuEvent.getGiftName();
            Price quantity = giftMenuEvent.getQuantity();
            return new GiftMenuDto(giftName, quantity);
        }
        return GiftMenuDto.defaultValue();
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

    private List<DiscountDetailDto> initDiscountDetails() {
        DiscountDto discountDto = getDiscountDto();
        ChristmasDiscount xmasDiscount = getXmasDiscount(discountDto);
        GiftMenuEvent giftMenuEvent = getGiftMenuEvent(discountDto);
        SpecialDiscount specialDiscount = getSpecialDiscount(discountDto);
        WeekdayDiscount weekdayDiscount = getWeekdayDiscount(discountDto);
        WeekendDiscount weekendDiscount = getWeekendDiscount(discountDto);

        return Stream.of(xmasDiscount, giftMenuEvent, specialDiscount, weekdayDiscount, weekendDiscount)
                .filter(Objects::nonNull)
                .map(type -> {
                    String discountName = type.toString();
                    Name name = new Name(discountName);
                    Price discountValue = type.getDiscountValue();
                    return new DiscountDetailDto(name, discountValue);
                }).toList();
    }

    private ChristmasDiscount getXmasDiscount(DiscountDto discountDto) {
        if (judgeXmasDiscount(visitDate) != null) {
            return discountDto.christmasDiscount();
        }
        return null;
    }

    private GiftMenuEvent getGiftMenuEvent(DiscountDto discountDto) {
        Price orderPrice = initTotalPrePrice();
        if (judgeGiftMenuEvent(orderPrice) != null) {
            return discountDto.giftMenuEvent();
        }
        return null;
    }

    private SpecialDiscount getSpecialDiscount(DiscountDto discountDto) {
        if (judgeSpecialDiscount(visitDate) != null) {
            return discountDto.specialDiscount();
        }
        return null;
    }

    private WeekdayDiscount getWeekdayDiscount(DiscountDto discountDto) {
        if (judgeWeekdayDiscount(visitDate, orders) != null) {
            return discountDto.weekdayDiscount();
        }
        return null;
    }

    private WeekendDiscount getWeekendDiscount(DiscountDto discountDto) {
        if (judgeWeekendDiscount(visitDate, orders) != null) {
            return discountDto.weekendDiscount();
        }
        return null;
    }

    private Price initTotalDiscountPrice() {

        List<DiscountDetailDto> discountDetailDtos = initDiscountDetails();
        int totalDiscountPrice = discountDetailDtos.stream()
                .filter(Objects::nonNull)
                .mapToInt(type -> type.discountPrice().value())
                .sum();

        return new Price(totalDiscountPrice);
    }

    private Price initTotalPostPrice() {
        int postValue = totalPrePrice.value() - totalDiscountPrice.value();

        DiscountDto discountDto = getDiscountDto();
        GiftMenuEvent giftMenuEvent = getGiftMenuEvent(discountDto);
        if (giftMenuEvent != null) {
            postValue = postValue + GIFT_MENU_AMOUNT;
        }

        return new Price(postValue);
    }

    private EventBadge initBadge() {
        return EventBadge.findByPrice(totalDiscountPrice);
    }

    public Price getTotalPrePrice() {
        return totalPrePrice;
    }

    public GiftMenuDto getGiftMenu() {
        return giftMenu;
    }

    public List<DiscountDetailDto> getDiscountDetails() {
        return discountDetails;
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public Price getTotalPostPrice() {
        return totalPostPrice;
    }

    public EventBadge getBadge() {
        return Badge;
    }
}
