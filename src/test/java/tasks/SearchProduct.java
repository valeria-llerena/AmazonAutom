package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import ui.AmazonPage;

public class SearchProduct implements Task {

    private final String product;

    public SearchProduct(String product) {
        this.product = product;
    }

    public static SearchProduct called(String product) {
        return new SearchProduct(product);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(product).into(AmazonPage.SEARCH_BOX),
                Click.on(AmazonPage.SEARCH_BUTTON)
        );
    }
}
