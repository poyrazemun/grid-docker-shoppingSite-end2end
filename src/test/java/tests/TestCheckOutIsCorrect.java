package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CheckOutStepOnePage;
import utils.ConvertJSONToListOfString;

import java.util.List;

public class TestCheckOutIsCorrect extends BaseTest {
    SoftAssert sa = new SoftAssert();
    public static CartPage cartPage;
    public static CheckOutStepOnePage checkOutStepOnePage;

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
        cartPage = productsPage.goToCartPage();
        checkOutStepOnePage = cartPage.goToCheckOutPage();
        checkOutStepOnePage.fillTheForm("a", "b", "c");
        //burda kaldim, simdi yeni bir JSON yazacagim ve bu bilgilerle product bilgisi de olacak.
        //Sonra her bir insan ve her bir product icin checkoutu tamamlayacagim.
        sa.assertTrue(cartPage.productIsRemovedFromCart(productName));
        sa.assertAll();
    }
}
