package christmas.view;

public class OutputView {
    public void introduce() {
        String message = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
        print(message);
    }

    public void preview(int date) {
        String message = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        print(message);
    }

    public void orderMenuTitle() {
        String message = "<주문 메뉴>";
        print(message);
    }

    public void preDiscountTitle() {
        String message = "<할인 전 총주문 금액>";
        print(message);
    }

    public void benefitTitle() {
        String message = "<혜택 내역>";
        print(message);
    }

    public void totalDiscountTitle() {
        String message = "<총혜택 금액>";
        print(message);
    }

    public void postDiscountTitle() {
        String message = "<할인 후 예상 결제 금액>";
        print(message);
    }

    public void eventBadgeTitle() {
        String message = "<12월 이벤트 배지>";
        print(message);
    }

    private void print(String message) {
        System.out.println(message);
    }
}
