package tasks;

import interactions.MoveSlider;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.AmazonPage;
import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class FilterByPrice implements Task {
    private final String min;
    private final String max;

    public FilterByPrice(String min, String max) {
        this.min = min;
        this.max = max;
    }

    public static FilterByPrice between(String min, String max) {
        return instrumented(FilterByPrice.class, min, max);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // --- PROCESAR PRECIO MÍNIMO ---
        if (AmazonPage.MIN_PRICE_SLIDER.resolveFor(actor).isVisible()) {
            actor.attemptsTo(
                    MoveSlider.to(AmazonPage.MIN_PRICE_SLIDER, min),
                    WaitUntil.the(AmazonPage.RESULTS_CONTAINER, isVisible()).forNoMoreThan(Duration.ofSeconds(5))
            );
        } else {
            System.out.println("DEBUG: Slider Mínimo no visible, saltando paso...");
        }

        // --- PROCESAR PRECIO MÁXIMO ---
        // Volvemos a verificar porque el DOM pudo cambiar tras el primer filtro
        if (AmazonPage.MAX_PRICE_SLIDER.resolveFor(actor).isVisible()) {
            actor.attemptsTo(
                    MoveSlider.to(AmazonPage.MAX_PRICE_SLIDER, max),
                    WaitUntil.the(AmazonPage.RESULTS_CONTAINER, isEnabled()).forNoMoreThan(Duration.ofSeconds(5))
            );
        } else {
            System.out.println("DEBUG: Slider Máximo no visible, saltando paso...");
        }
    }
}