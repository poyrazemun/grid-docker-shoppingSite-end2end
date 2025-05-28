package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConvertJSONToListOfString;

import java.util.List;

@SuppressWarnings("ALL")
public class TestAddProductToCart extends BaseTest {
    @DataProvider(name = "products")
    public Object[][] products() {
        List<String> productList = ConvertJSONToListOfString.getJSONDataToListOfString("src/test/java/data/products.json");
        Object[][] result = new Object[productList.size()][1];
        for (int i = 0; i < productList.size(); i++) {
            result[i][0] = productList.get(i);
        }
        return result;
    }

    @Feature("Adding Product to the Cart Feature")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "products", groups = {"loginRequired"}, description = "Verify that user can add a product to the cart.")
    public void testProductIsAddedToCart(String productName) {
        productsPage.addProductToCart(productName);
        Assert.assertTrue(productsPage.isProductAddedToCart(productName));
    }


}


