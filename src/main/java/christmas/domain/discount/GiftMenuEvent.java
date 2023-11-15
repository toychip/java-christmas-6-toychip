package christmas.domain.discount;

import static christmas.static_class.DiscountStatic.GIFT_EVENT_CRITERIA_VALUE;
import static christmas.static_class.DiscountStatic.GIFT_MENU_EVENT_NAME;
import static christmas.static_class.DiscountStatic.QUANTITY;

import christmas.domain.menu.Drink;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.exception.inside.discount.InvalidGiftMenuException;

public class GiftMenuEvent implements Discount{
    private final Price discountValue;
    private final Name giftName;
    private final Price quantity;

    public GiftMenuEvent(Price originalPrice) {
        validate(originalPrice);
        // TODO 불필요한 this 제거하기
        this.discountValue = discount();
        this.giftName = generateGiftName();
        quantity = generateQuantity();
    }

    private Price generateQuantity() {
        // 추후 이벤트가 바뀌어 개수 수정 필요시 이곳에서 수정
        return new Price(QUANTITY);
    }

    private void validate(Price originalPrice) {
        validateExceededAmount(originalPrice);
    }

    private void validateExceededAmount(Price originalPrice) {
        int value = originalPrice.getValue();
        if (value < GIFT_EVENT_CRITERIA_VALUE) {
            throw new InvalidGiftMenuException();
        }
    }

    @Override
    public Price discount() {
        return calculateDiscount();
    }

    private Price calculateDiscount() {
        Drink drink = generateGift();
        return drink.getPrice();
    }

    private Drink generateGift() {
        return Drink.generateGift();
    }

    private Name generateGiftName() {
        Drink drink = generateGift();
        return drink.getName();
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }

    public Name getGiftName() {
        return giftName;
    }

    public Price getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return GIFT_MENU_EVENT_NAME;
    }
}
