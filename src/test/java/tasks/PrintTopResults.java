package tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.AmazonPage;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PrintTopResults implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        // 1. Esperar a que al menos un resultado sea visible antes de empezar
        actor.attemptsTo(
                WaitUntil.the(AmazonPage.RESULTS, isVisible()).forNoMoreThan(10).seconds()
        );

        // 2. Obtener todos los contenedores
        List<WebElementFacade> resultList = AmazonPage.RESULTS.resolveAllFor(actor);

        System.out.println("--- TOP 5 RESULTADOS ---");

        int count = 0;
        for (WebElementFacade item : resultList) {
            if (count >= 5) break; // Detenerse cuando lleguemos a 5

            try {
                // Extraer título - Usamos textContent para ser más agresivos si el texto está oculto
                List<WebElementFacade> titleElements = item.thenFindAll(".//h2//span");
                if (titleElements.isEmpty()) continue; // Si no hay título, es un slot vacío o publicidad, saltar.

                String title = titleElements.get(0).getText().trim();
                if (title.isEmpty()) continue; // Si el título está vacío, saltar.

                // Extraer precio
                List<WebElementFacade> priceElements = item.thenFindAll(".//span[@class='a-price-whole']");
                String price = !priceElements.isEmpty() ? priceElements.get(0).getText().trim() : "N/A";

                count++;
                System.out.println(count + ". " + title + " | Precio: $" + price);

            } catch (Exception e) {
                // Ignorar errores menores y seguir con el siguiente
            }
        }

        if (count == 0) {
            System.out.println("No se encontraron productos válidos en la lista.");
        }
    }

    public static PrintTopResults topFive() {
        return new PrintTopResults();
    }
}