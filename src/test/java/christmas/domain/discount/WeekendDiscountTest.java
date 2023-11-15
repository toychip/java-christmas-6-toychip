package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.InvalidWeekendException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendDiscountTest {

    @ParameterizedTest
    @CsvSource({"3", "4", "5", "6", "7",
            "10", "11", "12", "13", "14",
            "17", "18", "19", "20", "21",
            "24", "25", "26", "27", "28",
            "31"})
    @DisplayName("주말 날짜 검증, 실패시 InvalidWeekendException")
    void 주말_날짜_검증(String userDateInput) {

        // given
        String userOrdersInput = "아이스크림-3, 티본스테이크- 2, 초코케이크-3";
        VisitDate visitDate = new VisitDate(userDateInput);
        Orders orders = new Orders(userOrdersInput);

        // when && then
        assertThrows(InvalidWeekendException.class,
                () -> new WeekendDiscount(visitDate, orders));
    }

    @ParameterizedTest
    @CsvSource("2, '아이스크림-3, 티본스테이크- 2, 초코케이크-3', 100000")
    @DisplayName("주말 할인 값 테스트")
    void 메뉴_개수에따른_주말_할인값(String userDateInput, String userOrdersInput) {

        // given
        VisitDate visitDate = new VisitDate(userDateInput);
        Orders orders = new Orders(userOrdersInput);

        // when
        WeekendDiscount weekendDiscount = new WeekendDiscount(visitDate, orders);
        Price discount = weekendDiscount.getDiscountValue();
        int discountValue = discount.value();

        // then
        assertEquals(discountValue, 2023 * 2);
    }
}