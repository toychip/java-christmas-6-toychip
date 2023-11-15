package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import java.util.List;
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
        visitDate = new VisitDate("3");
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

    @Test
    @DisplayName("총 혜택 금액 테스트")
    void totalDiscountPriceTest(){

        //when
        Price totalDiscountPrice = moneyManagement.getTotalDiscountPrice();
        int value = totalDiscountPrice.getValue();

        //then
        assertEquals(value, 31246);
    }

    @Test
    @DisplayName("할인 후 예상 결제 금액 테스트")
    void totalPostPriceTest(){

        //when
        Price totalPostPrice = moneyManagement.getTotalPostPrice();
        int value = totalPostPrice.getValue();

        //then
        assertEquals(value, 135754);
    }

    @Test
    @DisplayName("혜택 금액에 따라 배지 증정 테스트")
    void badgeTest(){

        //when
        Name badge = moneyManagement.getBadge();
        String name = badge.getName();

        //then
        assertEquals(name, "산타");
    }

    @Test
    @DisplayName("할인 상세 - 크리스마스 할인 테스트")
    void discountDetailXmasTest(){

        List<DiscountDetailDto> discountDetails = moneyManagement.getDiscountDetails();
        int christmasDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().getName().equals("ChristmasDiscount"))
                .mapToInt(type -> type.discountPrice().getValue())
                .sum();
        assertEquals(christmasDiscountValue, 1200);
    }


    @Test
    @DisplayName("할인 상세 - 평일 할인 테스트")
    void discountDetailWeekdayTest(){

        List<DiscountDetailDto> discountDetails = moneyManagement.getDiscountDetails();
        int christmasDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().getName().equals("WeekdayDiscount"))
                .mapToInt(type -> type.discountPrice().getValue())
                .sum();
        assertEquals(christmasDiscountValue, 4046);
    }


    @Test
    @DisplayName("할인 상세 - 특별 할인 금액 테스트")
    void discountDetailSpecialTest(){

        List<DiscountDetailDto> discountDetails = moneyManagement.getDiscountDetails();
        int christmasDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().getName().equals("SpecialDiscount"))
                .mapToInt(type -> type.discountPrice().getValue())
                .sum();
        assertEquals(christmasDiscountValue, 1000);
    }

    @Test
    @DisplayName("할인 상세 - 상품 증정 금액 테스트")
    void discountDetail_GiftMenuEventTest(){

        List<DiscountDetailDto> discountDetails = moneyManagement.getDiscountDetails();
        int christmasDiscountValue = discountDetails.stream()
                .filter(type -> type.discountName().getName().equals("GiftMenuEvent"))
                .mapToInt(type -> type.discountPrice().getValue())
                .sum();
        assertEquals(christmasDiscountValue, 25000);
    }

}