package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConvertStringToSlug;

public class CartPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CartPage.class);


    public CartPage() {
        super();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartIcon;

    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkOutButton;

    public By removeButtonXpath(String productName) {
        logger.info("remove-{}", ConvertStringToSlug.convertToSlug(productName));
        return new By.ById("remove-" + ConvertStringToSlug.convertToSlug(productName));
    }

    public WebElement getRemoveButton(String productName) {
        By removeButtonXpath = new By.ByXPath("//button[@id='remove-" + ConvertStringToSlug.convertToSlug(productName) + "']");
        return driver.findElement(removeButtonXpath);

    }

    public void removeProduct(String productName) {
        getRemoveButton(productName).click();
    }

    public boolean productIsRemovedFromCart(String productName) {
        return !shoppingCartIcon.getText().equals("1");
    }

    public CheckOutStepOnePage goToCheckOutPage() {
        clickToElement(checkOutButton);
        return new CheckOutStepOnePage();
    }
}
