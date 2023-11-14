package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDiscountTest {

    @ParameterizedTest
    @CsvSource("24, 20000, 3300")
    @DisplayName("크리스마스 할인 생성 - 할인 가격이 규칙과 일치하는지 확인하는 테스트")
    void 크리스마스_할인_생성_테스트(String userInput, int originalPriceValue, int expectDiscountValue) {
        // given
        VisitDate date = new VisitDate(userInput);
        Price originalPrice = new Price(originalPriceValue);

        // when
        ChristmasDiscount xmasDiscount = new ChristmasDiscount(date, originalPrice);
        Price discountPrice = xmasDiscount.getDiscountValue();
        int discountValue = discountPrice.getValue();

        // then
        assertEquals(discountValue, expectDiscountValue);
    }
}