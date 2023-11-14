package christmas.domain.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.VisitDate;
import christmas.exception.DateUnitException;
import christmas.exception.NotExistsMenuException;
import christmas.exception.OrderDuplicateMenuException;
import christmas.exception.OrderOnlyDrinkException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersTest {

    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @DisplayName("메뉴에 없는 주문 생성시 NotExistsMenuException 발생하며 공식 주문 오류메시지 출력")
    @ValueSource(strings = {"블루와인-2, 양송이스프-1", "초콜릿-2, 사탕-1"})
    void 메뉴에_없는_주문_테스트(String userInput) {
        //given

        //when && then
        NotExistsMenuException e = assertThrows(NotExistsMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), ORDER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("메뉴가 겹칠 때 OrderDuplicateMenuException 발생하며 공식 주문 오류메시지 출력")
    @ValueSource(strings = {"양송이스프-2, 양송이스프-1", "티본스테이크-2, 양송이스프-1, 티본스테이크-1"})
    void 메뉴가_겹칠때_테스트(String userInput) {
        //given
        //when && then
        OrderDuplicateMenuException e = assertThrows(
                OrderDuplicateMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), ORDER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("날짜가 올바르지 않을 때 DateUnitException 발생하며 공식 날짜 오류메시지 출력")
    @ValueSource(strings = {"32", "ㄱㄴㅇ", "2147483648"})
    void 날짜가_올바르지_않을_때(String userInput) {

        //when && then
        DateUnitException e = assertThrows(
                DateUnitException.class,
                () -> new VisitDate(userInput));
        assertEquals(e.getMessage(), DATE_ERROR_MESSAGE);
    }

//    @Test
    @ParameterizedTest
    @DisplayName("음료만 주문했을 때 OrderOnlyDrinkException 발생하며 공식 주문 오류 메시지 출력")
    @ValueSource(strings = {"레드와인-3, 샴페인- 1", "제로콜라-3, 레드와인- 1"})
    void 음료만_주문했을_때(String userInput) {
        OrderOnlyDrinkException e = assertThrows(
                OrderOnlyDrinkException.class,
                () -> new Orders(userInput));
        assertEquals(ORDER_ERROR_MESSAGE, e.getMessage());
    }
}