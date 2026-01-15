package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;

import ui.AmazonPage;

public class ApplyFilters implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(AmazonPage.BRAND_SKECHERS)
        );
    }

    public static ApplyFilters now() {
        return new ApplyFilters();
    }
}
