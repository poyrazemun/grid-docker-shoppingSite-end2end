package pages;

import base.BasePage;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {

    public ProductsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

}
