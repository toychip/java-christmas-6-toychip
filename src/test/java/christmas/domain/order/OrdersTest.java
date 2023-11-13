package christmas.domain.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.exception.NotExistsMenuException;
import christmas.exception.OrderDuplicateMenuException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {

    private static final String MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @Test
    @DisplayName("메뉴에 없는 주문 생성시 NotExistsMenuException 발생하며 공식 오류메시지 출력")
    void 메뉴에_없는_주문_테스트() {
        //given
        String userInput = "블루와인-2, 양송이스프-1";

        //when && then
        NotExistsMenuException e = Assertions.assertThrows(NotExistsMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), MESSAGE);
    }

    @Test
    @DisplayName("메뉴가 겹칠 때 OrderDuplicateMenuException 발생하며 공식 오류메시지 출력")
    void 메뉴가_겹칠때_테스트() {
        //given
        String userInput = "양송이스프-2, 양송이스프-1";

        //when && then
        OrderDuplicateMenuException e = Assertions.assertThrows(
                OrderDuplicateMenuException.class,
                () -> new Orders(userInput));
        assertEquals(e.getMessage(), MESSAGE);
    }

}