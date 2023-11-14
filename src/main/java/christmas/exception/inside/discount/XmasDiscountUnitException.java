package christmas.exception.inside.discount;

public class XmasDiscountUnitException extends IllegalArgumentException{
    private static final String MESSAGE = "[ERROR] 크리스마스 할인은 25일 이후에 사용될 수 없습니다 (내부 오류)";
    public XmasDiscountUnitException() {
        super(MESSAGE);
    }
}
