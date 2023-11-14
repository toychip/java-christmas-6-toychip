package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.InvalidStarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {

    @ParameterizedTest
    @CsvSource({"1", "2",
            "4", "5", "6", "7", "8", "9",
            "11", "12", "13", "14", "15", "16",
            "18", "19", "20", "21", "22", "23",
                  "26", "27", "28", "29", "30"})
    @DisplayName("특별 날짜 검증, 실패시 InvalidStarException")
    void 특별_날짜_검증(String userDateInput) {

        // given
        int value = 100000;
        VisitDate visitDate = new VisitDate(userDateInput);
        Price price = new Price(value);

        // when && then
        assertThrows(InvalidStarException.class,
                () -> new SpecialDiscount(visitDate, price));
    }

    @ParameterizedTest
    @CsvSource({"3", "10", "17", "24", "25", "31"})
    void 특별할인_값_테스트(String userInputDate){

        int userInputPrice = 10000;
        //given
        VisitDate visitDate = new VisitDate(userInputDate);
        Price price = new Price(userInputPrice);

        // when
        SpecialDiscount specialDiscount = new SpecialDiscount(visitDate, price);
        Price discountPrice = specialDiscount.getDiscountValue();
        int discountValue = discountPrice.getValue();

        //then
        assertEquals(discountValue, 1000);
    }

}