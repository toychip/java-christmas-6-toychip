package christmas.domain.discount;

import christmas.domain.menu.Drink;
import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;
import christmas.exception.discount.InvalidGiftMenuException;

public class GiftMenuEvent implements Discount{
    private final Price originalPrice;
    private final Price discountValue;
    private final Name giftName;

    public GiftMenuEvent(Price originalPrice) {
        validate(originalPrice);
        this.originalPrice = originalPrice;
        this.discountValue = discount();
        this.giftName = generateGiftName();
    }

    private void validate(Price originalPrice) {
        validateExceededAmount(originalPrice);
    }

    private void validateExceededAmount(Price originalPrice) {
        int value = originalPrice.getValue();
        if (value < 120000) {
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
    public Price getOriginalPrice() {
        return originalPrice;
    }

    @Override
    public Price getDiscountValue() {
        return discountValue;
    }

    public String getGiftName() {
        return giftName.getName();
    }
}
