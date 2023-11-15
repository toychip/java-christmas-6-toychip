package christmas.static_class;

public final class DateStatic {

    private DateStatic() {
        throw new AssertionError("인스턴스화 금지");
    }

    public static final int DATE_MIN = 1;
    public static final int DATE_MAX = 31;

}
