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
        return null;
    }

    private MoneyManagement initMoneyManagement() {
        return null;
    }

}
