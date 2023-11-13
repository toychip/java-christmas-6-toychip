package christmas.domain.menu.component;

public class Price {
    private final int value;

    public Price(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 가격은 0원 이하일 수 없음");
        }
    }

    public int getValue() {
        return value;
    }
}
