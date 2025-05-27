package tests;

import base.BaseTest;
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
        List<String> productList = ConvertJSONToListOfString.getJSONDataToListOfString("src/test/java/data/products.json");
        Object[][] result = new Object[productList.size()][1];
        for (int i = 0; i < productList.size(); i++) {
            result[i][0] = productList.get(i);
        }
        return result;
    }

    @Test(dataProvider = "products", priority = 1, groups = {"loginRequired"})
    public void testProductIsRemovedFromCart(String productName) {
        productsPage.addProductToCart(productName);
        sa.assertTrue(productsPage.isProductAddedToCart(productName));
        cartPage = productsPage.goToCartPage();
        cartPage.removeProduct(productName);
        sa.assertTrue(cartPage.productIsRemovedFromCart(productName));
        sa.assertAll();
    }
}
