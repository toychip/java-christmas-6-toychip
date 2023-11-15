package christmas.static_class;

public final class DateStatic {

    public static final int DATE_MIN = 1;
    public static final int DATE_MAX = 31;

    private DateStatic() {
        throw new AssertionError("인스턴스화 금지");
    }

}
