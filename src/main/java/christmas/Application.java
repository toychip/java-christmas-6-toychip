package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasController xmasController = new ChristmasController(new InputView(), new OutputView());
        xmasController.run();
    }
}
