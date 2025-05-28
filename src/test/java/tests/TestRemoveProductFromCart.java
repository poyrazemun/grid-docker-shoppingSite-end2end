package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import utils.ConvertJSONToListOfString;

import java.util.List;

public class TestRemoveProductFromCart extends BaseTest {

    SoftAssert sa = new SoftAssert();
    public static CartPage cartPage;


    @DataProvider(name = "products")
    public Object[][] products() {
        String productsFilePath = System.getProperty("productsFilePath", "src/test/java/data/products.json");
        List<String> productList = ConvertJSONToListOfString.getJSONDataToListOfString(productsFilePath);
        Object[][] result = new Object[productList.size()][1];
        for (int i = 0; i < productList.size(); i++) {
            result[i][0] = productList.get(i);
        }
        return result;
    }

    @Feature("Removing Product from the Cart Feature")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "products", groups = {"loginRequired"}, description = "Verify that user can remove a product from the cart.")
    public void testProductIsRemovedFromCart(String productName) {
        productsPage.addProductToCart(productName);
        sa.assertTrue(productsPage.isProductAddedToCart(productName));
        cartPage = productsPage.goToCartPage();
        cartPage.removeProduct(productName);
        sa.assertTrue(cartPage.productIsRemovedFromCart(productName));
        sa.assertAll();
    }
}
