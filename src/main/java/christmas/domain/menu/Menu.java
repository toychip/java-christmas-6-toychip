package christmas.domain.menu;

import java.util.List;

public interface Menu {

    List<Name> allName();
    Name getName();
    Price getPrice();
}
