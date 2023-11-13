package christmas.domain.menu.component;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return getValue() == price.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
