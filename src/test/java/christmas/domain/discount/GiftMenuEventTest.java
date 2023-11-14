package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.date.VisitDate;
import christmas.domain.menu.component.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftMenuEventTest {

    @ParameterizedTest
    @CsvSource("130000")
    void 증정_이벤트_혜택금액_테스트(int userInputPrice){
        //given
        Price price = new Price(userInputPrice);

        // when
        GiftMenuEvent giftMenuEvent = new GiftMenuEvent(price);
        Price discountPrice = giftMenuEvent.getDiscountValue();
        int priceValue = discountPrice.getValue();

        //then
        assertEquals(priceValue, 25000);
    }

}