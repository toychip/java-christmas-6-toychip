package christmas.controller;

import christmas.domain.MoneyManagement;
import christmas.domain.date.VisitDate;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        return null;
    }

}
