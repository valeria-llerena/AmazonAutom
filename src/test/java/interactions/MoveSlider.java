package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class MoveSlider implements Interaction {
    private final Target target;
    private final String value;

    public MoveSlider(Target target, String value) {
        this.target = target;
        this.value = value;
    }

    public static MoveSlider to(Target target, String value) {
        return instrumented(MoveSlider.class, target, value);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement slider = target.resolveFor(actor).getElement();

        // 1. Usar JS para establecer el valor exacto
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].value = arguments[1];", slider, value);

        // 2. Disparar eventos manuales para que React/Amazon se enteren del cambio
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                slider
        );

        // 3. Simular un pequeño movimiento físico (Drag and Drop insignificante)
        // Esto es lo que suele faltar para que Amazon procese el filtro AJAX
        Actions actions = new Actions(BrowseTheWeb.as(actor).getDriver());
        actions.clickAndHold(slider).moveByOffset(1, 0).release().perform();
    }
}