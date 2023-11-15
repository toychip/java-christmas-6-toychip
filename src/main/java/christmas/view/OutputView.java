package christmas.view;

import static christmas.static_class.CommonStatic.KOREA_BILL_UNIT;
import static christmas.static_class.CommonStatic.QUANTITY_UNIT;
import static christmas.static_class.CommonStatic.SPACE;

public class OutputView {
    public void introduce() {
        String message = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
        printMessage(message);
    }

    public void preview(int date) {
        String message = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        printMessage(message);
        enter();
    }

    public void orderMenuTitle() {
        String message = "<주문 메뉴>";
        printMessage(message);
    }

    public void orderMenuPrint(String orderMenu, int orderValue) {
        String message = orderMenu + SPACE + orderValue + QUANTITY_UNIT;
        printMessage(message);
    }

    public void preDiscount(int amount) {
        String message = "<할인 전 총주문 금액>";
        printMessage(message);
        printAmount(amount);
    }

    public void giftMenu() {
        String message = "<증정 메뉴>";
        printMessage(message);
    }

    public void printGiftMenu(String name, int quantity) {
        String message = name + SPACE + quantity + QUANTITY_UNIT;
        printMessage(message);
    }

    public void printGiftMenu(String name) {
        printMessage(name);
    }

    public void benefitTitle() {
        String message = "<혜택 내역>";
        printMessage(message);
    }

    public void benefitDetailXmas(int amount) {
        String message = "크리스마스 디데이 할인: -" + amount + KOREA_BILL_UNIT;
        printMessage(message);
    }

    public void benefitWeekday(int amount) {
        String message = "평일 할인: -" + amount + KOREA_BILL_UNIT;
        printMessage(message);
    }

    public void benefitWeekend(int amount) {
        String message = "주말 할인: -" + amount + KOREA_BILL_UNIT;
        printMessage(message);
    }

    public void benefitSpecial(int amount) {
        String message = "특별 할인: -" + amount + KOREA_BILL_UNIT;
        printMessage(message);
    }

    public void benefitGiftEvent(int amount) {
        String message = "증정 이벤트: -" + amount + KOREA_BILL_UNIT;
        printMessage(message);
    }

    public void totalDiscountTitle() {
        String message = "<총혜택 금액>";
        printMessage(message);
    }

    public void totalDiscountAmount(int amount) {
        String message = amount + KOREA_BILL_UNIT;
        if (amount > 0) {
            String head = "-";
            message = head + message;
        }
        printMessage(message);
        enter();
    }

    public void postDiscountTitle() {
        String message = "<할인 후 예상 결제 금액>";
        printMessage(message);
    }

    public void eventBadgeTitle() {
        String message = "<12월 이벤트 배지>";
        printMessage(message);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printAmount(int amount) {
        System.out.println(amount + KOREA_BILL_UNIT);
        enter();
    }

    public void enter() {
        System.out.println();
    }
}
