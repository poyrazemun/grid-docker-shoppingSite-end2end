package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutCompletePage extends BasePage {

    public CheckOutCompletePage() {
        super();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement thankYouForYourOrderElement;

    public String getTextOfThankYouForYourOrderElement() {
        return getText(thankYouForYourOrderElement);
    }

}
