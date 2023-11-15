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
        outputView.giftMenu();
        GiftMenuDto giftMenu = moneyManagement.getGiftMenu();

        Name menuName = giftMenu.menuName();
        String nameValue = menuName.name();
        Price quantity = giftMenu.quantity();
        int quantityValue = quantity.value();
        judgeGiftMenu(nameValue, quantityValue);
    }

    private void judgeGiftMenu(String nameValue, int quantityValue) {
        if(quantityValue <= 0){
            outputView.printGiftMenu(nameValue);
        }
        if (quantityValue > 0) {
            outputView.printGiftMenu(nameValue, quantityValue);
        }
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
