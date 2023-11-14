package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountTest {

    @ParameterizedTest
    @CsvSource("23, '아이스크림-3, 티본스테이크- 2, 초코케이크-3', 100000")
    @DisplayName("평일 할인 값 테스트")
    void 메뉴_개수에따른_평일_할인값(String userDateInput, String userOrdersInput, int value) {

        // given
        VisitDate visitDate = new VisitDate(userDateInput);
        Orders orders = new Orders(userOrdersInput);
        Price price = new Price(value);

        // when
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(visitDate, orders, price);
        Price discount = weekdayDiscount.getDiscountValue();
        int discountValue = discount.getValue();

        // then
        assertEquals(discountValue, 2023 * 6);
    }

}