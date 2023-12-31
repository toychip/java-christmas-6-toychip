package christmas.controller;

import static christmas.static_class.CommonStatic.NONE_NAME;

import christmas.domain.DiscountDetailDto;
import christmas.domain.GiftMenuDto;
import christmas.domain.MoneyManagement;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.EventBadge;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.exception.outside.DateUnitException;
import christmas.exception.outside.order.OrderParentsException;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final VisitDate visitDate;
    private final Orders orders;
    private final MoneyManagement moneyManagement;

    public ChristmasController(final InputView inputView, final OutputView outputView) {
        outputView.introduce();
        this.inputView = inputView;
        this.outputView = outputView;
        visitDate = initVisitDate();
        orders = initOrders();
        previewDate(outputView);
        moneyManagement = initMoneyManagement();
    }

    private void previewDate(final OutputView outputView) {
        int date = visitDate.getDate();
        outputView.preview(date);
    }

    private VisitDate initVisitDate() {
        try {
            String userDate = inputView.readDate();
            return new VisitDate(userDate);
        } catch (DateUnitException de) {
            outputView.printMessage(de.getMessage());
            return initVisitDate();
        }
    }

    private Orders initOrders() {
        try {
            String userMenu = inputView.readMenu();
            return new Orders(userMenu);
        } catch (OrderParentsException oe) {
            outputView.printMessage(oe.getMessage());
            return initOrders();
        }
    }

    private MoneyManagement initMoneyManagement() {
        return new MoneyManagement(visitDate, orders);
    }

    public void run() {
        orderMenu();
        totalPrePrice();
        giftMenu();
        benefitDetail();
        totalBenefitPrice();
        totalPostPrice();
        badge();
        inputView.closeConsole();
    }

    private void orderMenu() {
        outputView.orderMenuTitle();
        orders.getOrders()
                .forEach(this::orderMenuList);
        outputView.enter();
    }

    private void orderMenuList(final Order order) {
        String orderMenu = order.orderMenu().menuName();
        int quantity = order.orderValue().quantity();
        outputView.orderMenuPrint(orderMenu, quantity);
    }

    private void totalPrePrice() {
        Price totalPrePrice = moneyManagement.getTotalPrePrice();
        int priceValue = totalPrePrice.value();
        outputView.preDiscount(priceValue);
    }

    private void giftMenu() {
        outputView.giftMenu();
        GiftMenuDto giftMenu = moneyManagement.getGiftMenu();

        Name menuName = giftMenu.menuName();
        String nameValue = menuName.name();
        Price quantity = giftMenu.quantity();
        int quantityValue = quantity.value();
        judgeGiftMenu(nameValue, quantityValue);
    }

    private void judgeGiftMenu(final String nameValue, final int quantityValue) {
        if (quantityValue <= 0) {
            outputView.printGiftMenu(nameValue);
        }
        if (quantityValue > 0) {
            outputView.printGiftMenu(nameValue, quantityValue);
        }
        outputView.enter();
    }

    private void benefitDetail() {
        outputView.benefitTitle();
        List<DiscountDetailDto> discountDetails = moneyManagement.getDiscountDetails();

        detailDiscountXmas(discountDetails);
        detailDiscountWeekday(discountDetails);
        detailDiscountWeekend(discountDetails);
        detailDiscountSpecial(discountDetails);
        detailGiftMenu(discountDetails);
        detailNothing();
        outputView.enter();
    }

    private void detailDiscountXmas(final List<DiscountDetailDto> discountDetails) {
        int christmasDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().name().equals("ChristmasDiscount"))
                .mapToInt(type -> type.discountPrice().value())
                .sum();
        if (christmasDiscountValue != 0) {
            outputView.benefitDetailXmas(christmasDiscountValue);
        }
    }

    private void detailDiscountWeekday(final List<DiscountDetailDto> discountDetails) {
        int weekdayDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().name().equals("WeekdayDiscount"))
                .mapToInt(type -> type.discountPrice().value())
                .sum();

        if (weekdayDiscountValue != 0) {
            outputView.benefitWeekday(weekdayDiscountValue);
        }
    }

    private void detailDiscountWeekend(final List<DiscountDetailDto> discountDetails) {
        int weekendDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().name().equals("WeekendDiscount"))
                .mapToInt(type -> type.discountPrice().value())
                .sum();

        if (weekendDiscountValue != 0) {
            outputView.benefitWeekend(weekendDiscountValue);
        }
    }

    private void detailDiscountSpecial(final List<DiscountDetailDto> discountDetails) {
        int specialDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().name().equals("SpecialDiscount"))
                .mapToInt(type -> type.discountPrice().value())
                .sum();

        if (specialDiscountValue != 0) {
            outputView.benefitSpecial(specialDiscountValue);
        }
    }

    private void detailGiftMenu(final List<DiscountDetailDto> discountDetails) {
        int giftMenuEventValue = discountDetails.stream()
                .filter(type -> type.discountName().name().equals("GiftMenuEvent"))
                .mapToInt(type -> type.discountPrice().value())
                .sum();

        if (giftMenuEventValue != 0) {
            outputView.benefitGiftEvent(giftMenuEventValue);
        }
    }

    private void detailNothing() {
        Price totalDiscountPrice = moneyManagement.getTotalDiscountPrice();
        int totalDiscountValue = totalDiscountPrice.value();

        if (totalDiscountValue == 0) {
            outputView.printMessage(NONE_NAME);
        }
    }


    private void totalBenefitPrice() {
        outputView.totalDiscountTitle();
        Price totalDiscountPrice = moneyManagement.getTotalDiscountPrice();
        int totalDiscountValue = totalDiscountPrice.value();
        outputView.totalDiscountAmount(totalDiscountValue);
    }

    private void totalPostPrice() {
        outputView.postDiscountTitle();
        Price totalPostPrice = moneyManagement.getTotalPostPrice();
        int totalPostValue = totalPostPrice.value();
        outputView.printAmount(totalPostValue);
    }

    private void badge() {
        outputView.eventBadgeTitle();
        EventBadge badge = moneyManagement.getBadge();
        Name badgeName = badge.getBadgeName();
        String name = badgeName.name();
        outputView.printMessage(name);
    }

}
