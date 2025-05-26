package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class TestInvalidLogin extends BaseTest {

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginDetails() {
        List<HashMap<String, String>> data = getJSONDataToMap("src/test/java/data/invalidLoginCredentials.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)}
        };
    }

    @Test(dataProvider = "invalidLoginCredentials")
    public void testInvalidLogin(HashMap<String, String> data) {
        landingPage.login(data.get("username"), data.get("password"));
        String actualInvalidLoginMessage = landingPage.getTextOfInvalidLoginMessage();
        String expectedInvalidLoginMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualInvalidLoginMessage, expectedInvalidLoginMessage);

    }


}
