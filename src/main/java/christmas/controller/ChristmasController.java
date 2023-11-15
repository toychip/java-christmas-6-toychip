package christmas.controller;

import christmas.domain.DiscountDetailDto;
import christmas.domain.GiftMenuDto;
import christmas.domain.MoneyManagement;
import christmas.domain.date.VisitDate;
import christmas.domain.discount.EventBadge;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
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
        this.inputView = inputView;
        this.outputView = outputView;
        visitDate = initVisitDate();
        orders = initOrders();
        moneyManagement = initMoneyManagement();
    }

    private VisitDate initVisitDate() {
        String userDate = inputView.readDate();
        return new VisitDate(userDate);
    }

    private Orders initOrders() {
        String userMenu = inputView.readMenu();
        int date = visitDate.getDate();
        outputView.preview(date);
        return new Orders(userMenu);
    }

    private MoneyManagement initMoneyManagement() {
        return new MoneyManagement(visitDate, orders);
    }

    public void run() {
        outputView.introduce();
        totalPrePrice();
        giftMenu();
        benefitDetail();
        totalBenefitPrice();
        totalPostPrice();
        badge();
    }

    private void totalPrePrice() {
        Price totalPrePrice = moneyManagement.getTotalPrePrice();
        int priceValue = totalPrePrice.value();
        outputView.preDiscount(priceValue);
    }

    private void giftMenu() {
    }

    private void benefitDetail() {
    }

    private void totalBenefitPrice() {
    }

    private void totalPostPrice() {
    }

    private void badge() {
    }


}
