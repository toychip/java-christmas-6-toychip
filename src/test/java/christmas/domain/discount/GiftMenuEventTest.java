package christmas.domain.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.InvalidGiftMenuException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftMenuEventTest {

    private static final String MESSAGE = "[ERROR] 주문 금액이 120,000 이하인데, GiftMenu 생성을 시도했습니다. (내부 오류)";

    @ParameterizedTest
    @CsvSource("119999")
    @DisplayName("구입 금액이 기준 미만일 때 InvalidGiftMenuException이 발생한다.")
    void 증정_이벤트_구입_금액이_기준미만_테스트(int userInputPrice){
        //given
        Price price = new Price(userInputPrice);

        InvalidGiftMenuException e = assertThrows(InvalidGiftMenuException.class,
                () -> new GiftMenuEvent(price));

        // when && then
        assertEquals(e.getMessage(), MESSAGE);
    }

    @ParameterizedTest
    @CsvSource("130000")
    @DisplayName("증정 이벤트를 진행하여 혜택 금액이 요구사항에 맞는 값인지 테스트")
    void 증정_이벤트_혜택금액_테스트(int userInputPrice){
        //given
        Price price = new Price(userInputPrice);

        // when
        GiftMenuEvent giftMenuEvent = new GiftMenuEvent(price);
        Price discountPrice = giftMenuEvent.getDiscountValue();
        int priceValue = discountPrice.value();

        //then
        assertEquals(priceValue, 25000);
    }


    @ParameterizedTest
    @CsvSource("130000")
    @DisplayName("증정 이벤트를 진행하여 증정 상품이 요구사항에 맞는 값인지 테스트")
    void 증정_이벤트_증정_상품이름_테스트(int userInputPrice){
        //given
        Price price = new Price(userInputPrice);

        // when
        GiftMenuEvent giftMenuEvent = new GiftMenuEvent(price);
        String giftName = giftMenuEvent.getGiftName().name();

        //then
        assertEquals(giftName, "샴페인");
    }

}