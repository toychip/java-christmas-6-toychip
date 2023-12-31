package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.XmasDiscountUnitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountTest {

    @ParameterizedTest
    @CsvSource("24, 3300")
    @DisplayName("크리스마스 할인 생성 - 할인 가격이 규칙과 일치하는지 확인하는 테스트")
    void 크리스마스_할인_생성_테스트(String userInput, int expectDiscountValue) {
        // given
        VisitDate date = new VisitDate(userInput);

        // when
        ChristmasDiscount xmasDiscount = new ChristmasDiscount(date);
        Price discountPrice = xmasDiscount.getDiscountValue();
        int discountValue = discountPrice.value();

        // then
        assertEquals(discountValue, expectDiscountValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"26", "29", "30"})
    @DisplayName("크리스마스 할인 생성2 - 크리스마스 디데이 할인 종료 후에 생성 시도시 XmasDiscountUnitException (내부 오류)")
    void 크리스마스_할인_생성_테스트2(String userInput) {

        // given
        VisitDate date = new VisitDate(userInput);

        // when
        assertThrows(XmasDiscountUnitException.class,
                () -> new ChristmasDiscount(date));

    }
}