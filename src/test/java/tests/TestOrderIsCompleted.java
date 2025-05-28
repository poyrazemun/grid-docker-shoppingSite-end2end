package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckOutCompletePage;
import pages.CheckOutStepOnePage;
import pages.CheckOutStepTwoPage;
import utils.ConvertJSONToMap;

import java.util.HashMap;
import java.util.List;

public class TestOrderIsCompleted extends BaseTest {
    public static CartPage cartPage;
    public static CheckOutStepOnePage checkOutStepOnePage;
    public static CheckOutStepTwoPage checkOutStepTwoPage;
    public static CheckOutCompletePage checkOutCompletePage;

    @DataProvider(name = "productsAndPeople")
    public Object[][] productsAndPeople() {
        List<HashMap<String, Object>> data = ConvertJSONToMap.getJSONDataToMap("src/test/java/data/productsAndPeople.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)},
                {data.get(3)}
        };
    }

    @Feature("Placing an Order Feature")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "productsAndPeople", groups = {"loginRequired"}, description = "Verify that user can finish his/her order successfully.")
    public void testOrderIsCompleted(HashMap<String, Object> data) {
        productsPage.addProductToCart((String) data.get("product"));
        cartPage = productsPage.goToCartPage();
        checkOutStepOnePage = cartPage.goToCheckOutPage();
        checkOutStepTwoPage = checkOutStepOnePage.submitTheForm((String) data.get("firstName"), (String) data.get("lastName"), (String) data.get("postalCode"));
        checkOutCompletePage = checkOutStepTwoPage.goToCheckOutCompletePage();
        String expectedConfirmationMessage = "Thank you for your order!";
        String actualConfirmationMessage = checkOutCompletePage.getTextOfThankYouForYourOrderElement();
        Assert.assertEquals(actualConfirmationMessage, expectedConfirmationMessage);

    }
}
