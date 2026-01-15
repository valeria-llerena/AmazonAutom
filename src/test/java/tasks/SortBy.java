package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.actions.Scroll;
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
                // 1. Scroll suave hasta el elemento para que sea visible
                Scroll.to(AmazonPage.SORT_DROPDOWN),

                // 2. Esperar a que el elemento esté listo
                WaitUntil.the(AmazonPage.SORT_DROPDOWN, isVisible()).forNoMoreThan(10).seconds(),

                // 3. Abrir el dropdown usando JS para saltar el bloqueo del Header
                JavaScriptClick.on(AmazonPage.SORT_DROPDOWN),

                // 4. Esperar que la opción (lowToHigh, etc.) sea visible
                WaitUntil.the(option, isVisible()).forNoMoreThan(5).seconds(),

                // 5. Clic en la opción con JS
                JavaScriptClick.on(option)
        );
    }
}