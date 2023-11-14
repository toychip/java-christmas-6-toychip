package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import christmas.exception.inside.discount.InvalidWeekdayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountTest {

    @ParameterizedTest
    @CsvSource({"1", "2", "8", "9", "15", "16", "22", "23", "29", "30"})
    @DisplayName("평일 날짜 검증, 실패시 InvalidWeekDayException")
    void 평일_날짜_검증(String userDateInput) {

        // given
        String userOrdersInput = "아이스크림-3, 티본스테이크- 2, 초코케이크-3";
        VisitDate visitDate = new VisitDate(userDateInput);
        Orders orders = new Orders(userOrdersInput);

        // when && then
        assertThrows(InvalidWeekdayException.class,
                () -> new WeekdayDiscount(visitDate, orders));
    }

    @ParameterizedTest
    @CsvSource("3, '아이스크림-3, 티본스테이크- 2, 초코케이크-3'")
    @DisplayName("평일 할인 값 테스트")
    void 메뉴_개수에따른_평일_할인값(String userDateInput, String userOrdersInput) {

        // given
        VisitDate visitDate = new VisitDate(userDateInput);
        Orders orders = new Orders(userOrdersInput);

        // when
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(visitDate, orders);
        Price discount = weekdayDiscount.getDiscountValue();
        int discountValue = discount.getValue();

        // then
        assertEquals(discountValue, 2023 * 6);
    }

}