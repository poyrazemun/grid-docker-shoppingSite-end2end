package tests;

import base.BaseTest;
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

    @Test(dataProvider = "products", priority = 1, groups = {"loginRequired"})
    public void testProductIsAddedToCart(String productName) {
        productsPage.addProductToCart(productName);
        Assert.assertTrue(productsPage.isProductAddedToCart(productName));
    }


}


