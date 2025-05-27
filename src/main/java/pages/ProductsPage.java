package pages;

import base.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCart;

    @Getter
    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    private List<WebElement> allProducts;


    public boolean isLoginSuccessful() {
        return isElementVisible(shoppingCart);
    }

    By addToCartButtonXpath = new By.ByXPath("./ancestor::div[1]/following-sibling::div[1]//button[normalize-space(text())='Add to cart']");
    By removeButtonXpath = new By.ByXPath("./ancestor::div[1]/following-sibling::div[1]//button[normalize-space(text())='Remove']");

    public WebElement getProductByName(String productName) {
        return getAllProducts().stream().filter(s -> s.getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        clickToElement(getProductByName(productName).findElement(addToCartButtonXpath));
    }

    public boolean isProductAddedToCart(String productName) {
        return getProductByName(productName).findElement(removeButtonXpath).getText().equals("Remove");
    }

    public CartPage goToCartPage() {
        clickToElement(shoppingCart);
        return new CartPage();
    }

}
