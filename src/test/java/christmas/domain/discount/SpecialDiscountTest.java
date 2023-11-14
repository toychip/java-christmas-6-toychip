package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.VisitDate;
import christmas.domain.menu.component.Price;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {

    @ParameterizedTest
    @CsvSource({"23, 10000", "24, 13131"})
    void 특별할인_테스트(String userInputDate, int userInputPrice){

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