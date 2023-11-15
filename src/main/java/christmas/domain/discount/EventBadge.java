package christmas.domain.discount;

import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;

public enum EventBadge {

    NONE(new Name("없음"), new Price(0)),
    STAR(new Name("별"), new Price(5000)),
    TREE(new Name("트리"), new Price(10000)),
    SANTA(new Name("산타"), new Price(20000))
    ;
    private final Name badgeName;
    private final Price totalDiscountPrice;

    EventBadge(Name badgeName, Price totalDiscountPrice) {
        this.badgeName = badgeName;
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public Name getBadgeName() {
        return badgeName;
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }
}
