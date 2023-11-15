package christmas.static_class;

public final class DiscountStatic {

    // 크리스마스 할인 사용 상수
    public static final int XMAS_DATE = 25;
    public static final int XMAS_MULTIPLE_UNIT = 100;
    public static final int XMAS_PLUS_UNIT = 1000;
    public static final int XMAS_MinusUnit = 100;
    public static final String XMAS_DISCOUNT_NAME = "ChristmasDiscount";


    // 이벤트 배지 사용 상수
    public static final int STAR_PRICE = 5000;
    public static final String STAR_NAME = "별";
    public static final int TREE_PRICE = 10000;
    public static final String TREE_NAME = "트리";
    public static final int SANTA_PRICE = 20000;
    public static final String SANTA_NAME = "산타";


    // 증정 이벤트 사용 상수
    public static final int QUANTITY = 1;
    public static final int GIFT_EVENT_CRITERIA_VALUE = 120000;
    public static final String GIFT_MENU_EVENT_NAME = "GiftMenuEvent";
    public static final int GIFT_MENU_AMOUNT = 25000;


    // 특별 할인 사용 상수
    public static final int SPECIAL_DISCOUNT_VALUE = 1000;
    public static final String SPECIAL_DISCOUNT_NAME= "SpecialDiscount";


    // 주말, 평일 할인 사용 상수
    public static final int WEEK_DISCOUNT_UNIT = 2023;
    public static final String WEEKDAY_DISCOUNT_NAME= "WeekdayDiscount";
    public static final String WEEKEND_DISCOUNT_NAME= "WeekendDiscount";

    private DiscountStatic() {
        throw new AssertionError("인스턴스화 금지");
    }
}
