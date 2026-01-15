package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.AmazonPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SortBy implements Task {
    private final Target option;

    public SortBy(Target option) {
        this.option = option;
    }

    // --- MÉTODOS ESTÁTICOS ---

    public static SortBy newest() {
        return instrumented(SortBy.class, AmazonPage.OPTION_NEWEST);
    }

    public static SortBy customerReview() {
        return instrumented(SortBy.class, AmazonPage.OPTION_CLIENT_SCORE);
    }

    public static SortBy lowToHigh() {
        return instrumented(SortBy.class, AmazonPage.OPTION_LOW_TO_HIGH);
    }

    public static SortBy highToLow() {
        return instrumented(SortBy.class, AmazonPage.OPTION_HIGH_TO_LOW);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // 1. Abrir el menú
                WaitUntil.the(AmazonPage.SORT_DROPDOWN, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(AmazonPage.SORT_DROPDOWN),

                // 2. Esperar a que la opción específica sea visible en el popover
                WaitUntil.the(option, isVisible()).forNoMoreThan(5).seconds(),

                // 3. Clic forzado con JS para ignorar bloqueos de animación o capas invisibles
                JavaScriptClick.on(option)
        );
    }
}