package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    public LandingPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3")
    private WebElement invalidLoginMessageElement;

    public ProductsPage login(String email, String password) {
        typeIntoTheElement(userNameInput, email);
        typeIntoTheElement(passwordInput, password);
        clickToElement(loginButton);
        return new ProductsPage();
    }

    public String getTextOfInvalidLoginMessage() {
        return getText(invalidLoginMessageElement);
    }


}
