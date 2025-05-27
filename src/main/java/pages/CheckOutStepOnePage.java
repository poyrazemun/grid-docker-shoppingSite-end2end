package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStepOnePage extends BasePage {

    public CheckOutStepOnePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='title']")
    private WebElement titleOfThePage;

    public String getTextOfTitle() {
        return getText(titleOfThePage);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCodeInputField;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    public void fillTheForm(String firstName, String lastName, String postalCode) {
        typeIntoTheElement(firstNameInputField, firstName);
        typeIntoTheElement(lastNameInputField, lastName);
        typeIntoTheElement(postalCodeInputField, postalCode);
    }

    public CheckOutStepTwoPage submitTheForm(String firstName, String lastName, String postalCode) {
        fillTheForm(firstName, lastName, postalCode);
        clickToElement(continueButton);
        return new CheckOutStepTwoPage();
    }


}
