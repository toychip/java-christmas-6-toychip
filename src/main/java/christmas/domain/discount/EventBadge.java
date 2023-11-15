package christmas.domain.discount;

import static christmas.static_class.CommonStatic.NONE_NAME;
import static christmas.static_class.DiscountStatic.SANTA_NAME;
import static christmas.static_class.DiscountStatic.SANTA_PRICE;
import static christmas.static_class.DiscountStatic.STAR_NAME;
import static christmas.static_class.DiscountStatic.STAR_PRICE;
import static christmas.static_class.DiscountStatic.TREE_NAME;
import static christmas.static_class.DiscountStatic.TREE_PRICE;

import christmas.domain.menu.component.Name;
import christmas.domain.menu.component.Price;

public enum EventBadge {

    NONE(new Name(NONE_NAME), new Price(0)),
    STAR(new Name(STAR_NAME), new Price(STAR_PRICE)),
    TREE(new Name(TREE_NAME), new Price(TREE_PRICE)),
    SANTA(new Name(SANTA_NAME), new Price(SANTA_PRICE));
    private final Name badgeName;
    private final Price totalDiscountPrice;

    EventBadge(Name badgeName, Price totalDiscountPrice) {
        this.badgeName = badgeName;
        this.totalDiscountPrice = totalDiscountPrice;
    }

    public static EventBadge findByPrice(Price price) {
        if (price.value() > getSantaCriteria().value()) {
            return SANTA;
        }

        if (price.value() > getTreeCriteria().value()) {
            return TREE;
        }

        if (price.value() > getStarCriteria().value()) {
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
