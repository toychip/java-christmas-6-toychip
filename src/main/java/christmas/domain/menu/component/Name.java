package christmas.domain.menu.component;

import java.util.Objects;

public record Name(String name) {

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name(), name1.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name());
    }
}
