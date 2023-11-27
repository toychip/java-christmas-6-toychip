package christmas.exception.inside.discount;

public class NotExistsMenuInsideException extends IllegalArgumentException {
    private static final String MESSAGE = "[ERROR] 존재하지 않는 메뉴이름 (내부 오류)";

    public NotExistsMenuInsideException() {
        super(MESSAGE);
    }
}
