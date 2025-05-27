package pages;

import base.BasePage;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepTwoPage extends BasePage {
    public CheckOutStepTwoPage() {
        super();
        PageFactory.initElements(driver, this);
    }
}
