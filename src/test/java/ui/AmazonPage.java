package ui;

import net.serenitybdd.screenplay.targets.Target;

public class AmazonPage {

    public static final Target SEARCH_BOX =
            Target.the("Search box")
                    .locatedBy("#twotabsearchtextbox");

    public static final Target SEARCH_BUTTON =
            Target.the("Search button")
                    .locatedBy("#nav-search-submit-button");

    public static final Target MIN_PRICE_SLIDER = Target.the("Min price slider")
            .locatedBy("#p_36\\/range-slider_slider-item_lower-bound-slider");

    public static final Target MAX_PRICE_SLIDER = Target.the("Max price slider")
            .locatedBy("#p_36\\/range-slider_slider-item_upper-bound-slider");

    public static final Target RESULTS_CONTAINER = Target.the("Search results area")
            .locatedBy(".s-main-slot");


    public static final Target BRAND_SKECHERS =
            Target.the("Skechers brand")
                    .locatedBy("//span[text()='Skechers']/preceding-sibling::div");


    public static final Target SORT_DROPDOWN = Target.the("Botón de ordenar por")
            .locatedBy("#a-autoid-0-announce, #a-autoid-1-announce, .a-dropdown-container");
    // Intentará varios selectores comunes de Amazon o la clase del contenedor
//    public static final Target OPTION_LOW_TO_HIGH = Target.the("Opción: Precio de bajo a alto")
//            .locatedBy("#s-result-sort-select_1");
//
//    public static final Target OPTION_NEWEST = Target.the("Opción: Llegadas más recientes")
//            .locatedBy("s-result-sort-select_4");
//
//    public static final Target OPTION_CLIENT_SCORE = Target.the("Opción: Opinión media de los clientes")
//            .locatedBy("s-result-sort-select_3");

    public static final Target OPTION_CLIENT_SCORE = Target.the("Opción: Promedio Opinión del cliente")
            .locatedBy("//a[contains(text(),'Promedio') and contains(text(),'del cliente')]");

    public static final Target OPTION_LOW_TO_HIGH = Target.the("Opción: Precio: Del más bajo al más alto")
            .locatedBy("//a[contains(text(),'más bajo')]");

    public static final Target OPTION_HIGH_TO_LOW = Target.the("Opción: Precio: Del más alto al más bajo")
            .locatedBy("//a[contains(text(),'más alto')]");

    public static final Target OPTION_NEWEST = Target.the("Opción: Llegadas más recientes")
            .locatedBy("//a[contains(text(),'recientes')]");

    public static final Target OPTION_NORMAL = Target.the("Opción: Destacados")
            .locatedBy("//a[contains(text(),'Destacados')]");

    public static final Target RESULTS =
            Target.the("Results")
                    .locatedBy("//div[@data-component-type='s-search-result']");


}
