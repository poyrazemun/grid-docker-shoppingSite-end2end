package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckOutStepOnePage;
import pages.CheckOutStepTwoPage;
import utils.ConvertJSONToMap;

import java.util.HashMap;
import java.util.List;

public class TestCheckOutIsCorrect extends BaseTest {

    public static CartPage cartPage;
    public static CheckOutStepOnePage checkOutStepOnePage;
    public static CheckOutStepTwoPage checkOutStepTwoPage;

    @DataProvider(name = "productsAndPeople")
    public Object[][] productsAndPeople() {
        String jsonFilePath = System.getProperty("jsonFilePath", "src/test/java/data/productsAndPeople.json");
        List<HashMap<String, Object>> data = ConvertJSONToMap.getJSONDataToMap(jsonFilePath);
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)},
                {data.get(3)}
        };
    }

    @Feature("Feature Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "productsAndPeople", groups = {"loginRequired"}, description = "Verify that user can checkout successfully.")
    public void testCheckoutIsCorrect(HashMap<String, Object> data) {
        productsPage.addProductToCart((String) data.get("product"));
        cartPage = productsPage.goToCartPage();
        checkOutStepOnePage = cartPage.goToCheckOutPage();
        checkOutStepTwoPage = checkOutStepOnePage.submitTheForm((String) data.get("firstName"), (String) data.get("lastName"), (String) data.get("postalCode"));
        Assert.assertTrue(checkOutStepTwoPage.isCheckOutCorrect(), "Checkout is not correct!");

    }
}
