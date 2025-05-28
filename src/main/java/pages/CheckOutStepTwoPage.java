package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepTwoPage extends BasePage {
    public CheckOutStepTwoPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@data-test='payment-info-value']")
    private WebElement paymentInformationValueElement;

    @FindBy(xpath = "//div[@data-test='shipping-info-value']")
    private WebElement shippingInformationValueElement;

    @FindBy(xpath = "//div[@data-test='total-label']")
    private WebElement priceInformationValueElement;

    public boolean isCheckOutCorrect() {
        return isElementVisible(paymentInformationValueElement) && isElementVisible(shippingInformationValueElement) && isElementVisible(priceInformationValueElement);
    }

    public CheckOutCompletePage goToCheckOutCompletePage() {
        clickToElement(finishButton);
        return new CheckOutCompletePage();
    }

}
