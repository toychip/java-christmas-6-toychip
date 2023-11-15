package christmas.static_class;

public final class MenuStatic {

    private MenuStatic() {
        throw new AssertionError("인스턴스화 금지");
    }

    // Appetizer Menu 사용 상수
    public static final String PINE_MUSHROOM_SOUP_NAME = "양송이수프";
    public static final int PINE_MUSHROOM_SOUP_PRICE = 6000;

    public static final String TAPAS_NAME = "타파스";
    public static final int TAPAS_PRICE = 5500;

    public static final String CAESAR_SALAD_NAME = "시저샐러드";
    public static final int CAESAR_SALAD_PRICE = 8000;


    // Desert Menu 사용 상수
    public static final String CHOCO_CAKE_NAME = "초코케이크";
    public static final int CHOCO_CAKE_PRICE = 15000;

    public static final String ICE_CREAM_NAME = "아이스크림";
    public static final int ICE_CREAM_PRICE = 5000;


    // Drink Menu 사용 상수
    public static final String ZERO_COLA_NAME = "제로콜라";
    public static final int ZERO_COLA_PRICE = 3000;

    public static final String RED_WINE_NAME = "레드와인";
    public static final int RED_WINE_PRICE = 60000;

    public static final String CHAMPAGNE_NAME = "샴페인";
    public static final int CHAMPAGNE_PRICE = 25000;


    // Main Menu 사용 상수
    public static final String T_BONE_STEAK_NAME = "티본스테이크";
    public static final int T_BONE_STEAK_PRICE = 55000;

    public static final String BBQ_RIB_NAME = "바비큐립";
    public static final int BBQ_RIB_PRICE = 54000;

    public static final int SEAFOOD_PASTA_PRICE = 35000;
    public static final String SEAFOOD_PASTA_NAME = "해산물파스타";

    public static final int CHRISTMAS_PASTA_PRICE = 25000;
    public static final String CHRISTMAS_PASTA_NAME = "크리스마스파스타";

}
