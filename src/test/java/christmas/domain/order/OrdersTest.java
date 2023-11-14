package christmas.domain.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.VisitDate;
import christmas.exception.DateUnitException;
import christmas.exception.NotExistsMenuException;
import christmas.exception.OrderDuplicateMenuException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersTest {

    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @Test
    @DisplayName("메뉴에 없는 주문 생성시 NotExistsMenuException 발생하며 공식 주문 오류메시지 출력")
    void 메뉴에_없는_주문_테스트() {
        //given
        String userInput = "블루와인-2, 양송이스프-1";

        //when && then
        NotExistsMenuException e = assertThrows(NotExistsMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), ORDER_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("메뉴가 겹칠 때 OrderDuplicateMenuException 발생하며 공식 주문 오류메시지 출력")
    void 메뉴가_겹칠때_테스트() {
        //given
        String userInput = "양송이스프-2, 양송이스프-1";

        //when && then
        OrderDuplicateMenuException e = assertThrows(
                OrderDuplicateMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), ORDER_ERROR_MESSAGE);
    }

    @DisplayName("날짜가 올바르지 않을 때 DateUnitException 발생하며 공식 날짜 오류메시지 출력")
    @ValueSource(strings = {"32", "ㄱㄴㅇ", "2147483648"})
    @ParameterizedTest
    void 날짜가_올바르지_않을_때(String userInput) {

        //when && then
        DateUnitException e = assertThrows(
                DateUnitException.class,
                () -> new VisitDate(userInput));
        assertEquals(e.getMessage(), DATE_ERROR_MESSAGE);
    }

}