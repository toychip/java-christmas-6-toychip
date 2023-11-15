package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyManagementTest {

    private Orders orders;
    private VisitDate visitDate;
    private MoneyManagement moneyManagement;

    //given
    @BeforeEach
    void setUp() {
        orders = new Orders("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        visitDate = new VisitDate("13");
        moneyManagement = new MoneyManagement(visitDate, orders);
    }

    @Test
    @DisplayName("주문 금액이 맞는지 확인하는 테스트")
    void totalPriceTest(){

        //when
        Price totalPrePrice = moneyManagement.getTotalPrePrice();
        int priceValue = totalPrePrice.getValue();

        //then
        assertEquals(priceValue, 142000);
    }

    @Test
    @DisplayName("혜택에 맞는 증정 상품을 증정했지 확인하는 테스트")
    void giftNameTest(){

        //when
        Name giftName = moneyManagement.getGiftName();
        String name = giftName.getName();
        String 샴페인 = "샴페인";

        //then
        assertEquals(name, 샴페인);
    }
}