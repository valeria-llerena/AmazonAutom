package runners;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import tasks.*;

@RunWith(SerenityRunner.class)
public class AmazonTest {

    Actor user = Actor.named("Valeria");

    @Managed(driver = "chrome")
    WebDriver driver;

    @Before
    public void setUp() {
        user.can(BrowseTheWeb.with(driver));
    }

    @Test
    public void search_shoes_on_amazon() {
        user.attemptsTo(
                OpenAmazon.page(),
                SearchProduct.called("Zapatos"),
                ApplyFilters.now(),
                FilterByPrice.between("100", "200"),
                SortBy.lowToHigh(),
                PrintTopResults.topFive()

        );
    }
}
