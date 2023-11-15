package christmas.static_class;

public final class CommonStatic {
    public static final String NONE_NAME = "없음";
    public static final int ORDER_MAX_UNIT = 20;
    public static final String ORDER_SPLITTER = ",";
    public static final String MENU_QUANTITY_SPLITTER = "-";
    public static final String KOREA_BILL_UNIT = "원";
    public static final int MENU_LOCATION = 0;
    public static final int QUANTITY_LOCATION = 1;
    public static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private CommonStatic() {
        throw new AssertionError("인스턴스화 금지");
    }



}
