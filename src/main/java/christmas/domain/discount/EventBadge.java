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

    public static EventBadge findByPrice(Price price) {
        if (price.getValue() > getSantaCriteria().getValue()) {
            return SANTA;
        }

        if (price.getValue() > getTreeCriteria().getValue()) {
            return TREE;
        }

        if (price.getValue() > getStarCriteria().getValue()) {
            return STAR;
        }

        return NONE;
    }

    private static Price getSantaCriteria() {
        return SANTA.getTotalDiscountPrice();
    }

    private static Price getTreeCriteria() {
        return TREE.getTotalDiscountPrice();
    }

    private static Price getStarCriteria() {
        return STAR.getTotalDiscountPrice();
    }

    public Name getBadgeName() {
        return badgeName;
    }

    public Price getTotalDiscountPrice() {
        return totalDiscountPrice;
    }
}
