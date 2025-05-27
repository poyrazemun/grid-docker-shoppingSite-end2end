package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LandingPage.class);


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

    public ProductsPage login(String username, String password) {
        typeIntoTheElement(userNameInput, username);
        typeIntoTheElement(passwordInput, password);
        clickToElement(loginButton);
        logger.info("Login credentials -> username: {} and password: {}", username, password);
        return new ProductsPage();
    }

    public String getTextOfInvalidLoginMessage() {
        return getText(invalidLoginMessageElement);
    }


}
