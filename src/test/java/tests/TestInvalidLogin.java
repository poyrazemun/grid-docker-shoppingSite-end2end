package tests;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ConvertJSONToMap;

import java.util.HashMap;
import java.util.List;

public class TestInvalidLogin extends BaseTest {

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginDetails() {
        String invalidLoginFilePath = System.getProperty("invalidLoginFilePath", "src/test/java/data/invalidLoginCredentials.json");
        List<HashMap<String, Object>> data = ConvertJSONToMap.getJSONDataToMap(invalidLoginFilePath);
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)}
        };
    }

    @Feature("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "invalidLoginCredentials", groups = {"noLogin"}, description = "Verify that user can not login with invalid credentials")
    public void testInvalidLogin(HashMap<String, Object> data) {
        landingPage.login((String) data.get("username"), (String) data.get("password"));
        String actualInvalidLoginMessage = landingPage.getTextOfInvalidLoginMessage();
        String expectedInvalidLoginMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualInvalidLoginMessage, expectedInvalidLoginMessage);

    }


}
