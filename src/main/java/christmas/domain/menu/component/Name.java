package christmas.domain.menu.component;

public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        // ... 이름이 에피타이저, 메인, 디저트, 음료에 포함되어있는지 확인
    }

    public String getName() {
        return name;
    }
}
