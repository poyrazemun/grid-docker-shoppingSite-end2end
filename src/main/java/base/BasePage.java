package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //to find the elements with wait
    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    //click an element
    public void clickToElement(By locator) {
        findElement(locator).click();
    }

    public void clickToElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    //sendKeys
    public void typeIntoTheElement(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void typeIntoTheElement(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
    }

    //Get text of an element
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    //check if element is visible
    public boolean isElementVisible(By locator) {
        try {
            findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    //Wait for element to be clickable
    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //navigate to a URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    //get the title of the page
    public String getPageTitle() {
        return driver.getTitle();
    }


}
