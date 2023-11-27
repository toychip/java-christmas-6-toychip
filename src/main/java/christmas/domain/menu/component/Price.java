package christmas.domain.menu.component;

import christmas.exception.inside.PriceUnitException;
import java.util.Objects;

public record Price(int value) {
    public Price {
        validate(value);
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new PriceUnitException();
        }
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
        return value() == price.value();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }
}
