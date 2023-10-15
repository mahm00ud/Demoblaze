package DataDriven;

import org.testng.annotations.DataProvider;

public class DP {

/*    @DataProvider(name = "loginData")
    public static Object[][] loginCredentials(){

        Object[][] credentials = new Object[][]{{"tomsmith","SuperSecretPassword!"},
                {"username2","password2"}};
        return credentials;
    }*/

    @DataProvider(name = "categoriesList")
    public static Object[][] categoriesList(){

        Object[][] categories = new Object[][]{
                {"Phones"},
                {"Laptops"},
                {"Monitors"}};
        return categories;
    }

}
