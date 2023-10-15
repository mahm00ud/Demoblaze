package tests;

import DataDriven.DP;
import org.testng.Assert;
import org.testng.annotations.Test;
import static utils.extentReports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import static utils.dataDriven.ExcelReader.getExcelValueByAttribute;

public class TestCases extends TestBase {

    @Test(priority = 0,description = "Register successfully and Login with the same credentials")
    public void verifySuccessfullRegisterAndLogin(Method method){
        startTest(method.getName(), "Register successfully and Login with the same credentials");

        homePage.goToSignUpModal();
        homePage.register(getExcelValueByAttribute("Credentials","username"),
                           getExcelValueByAttribute("Credentials","password"));

        softAssert.assertEquals(homePage.getAlertText(),"Sign up successful.");
        homePage.acceptAlert();

        homePage.goToLoginModal();
        homePage.loginAfterRegisteration();
        softAssert.assertEquals(homePage.getUsernameAfterLogin(),"Welcome "+ homePage.getUsername());
        softAssert.assertAll();
    }

    @Test(priority = 1,dataProvider = "categoriesList", dataProviderClass = DP.class,description = "Login then Verify that all categories has items")
    public void verifyCategoriesHaveItems(String category, Method method){
        startTest(method.getName(), "Login then Verify that all categories has items");

/*
        homePage.goToLoginModal();
        homePage.login(getExcelValueByAttribute("Credentials","username"),
                getExcelValueByAttribute("Credentials","password"));

        Assert.assertEquals(homePage.getUsernameAfterLogin(),"Welcome "+ homePage.getUsername());
*/

        homePage.clickOnCategory(category);
        Assert.assertTrue(categoryPage.countItems() > 0, "There are no items in this category as the count is " + categoryPage.countItems() );
    }

    @Test(priority = 3,description = "Verify items are added and removed from cart successfully")
    public void verifyAddAndRemoveFromCart(Method method){
        startTest(method.getName(), "Verify items are added and removed from cart successfully");

        homePage.goToLoginModal();
        homePage.login(getExcelValueByAttribute("Credentials","username"),
                getExcelValueByAttribute("Credentials","password"));

        Assert.assertEquals(homePage.getUsernameAfterLogin(),"Welcome "+ homePage.getUsername());

        homePage.clickOnCategory("Phones");
        categoryPage.clickOnRandomItem();
        softAssert.assertTrue(productPage.isAddToCartDisplayed());
        String productName =  productPage.getProductName();

        productPage.clickOnAddToCart();
        softAssert.assertEquals(productPage.getAddToCartSuccessMsg(),"Product added.");
        productPage.acceptAlert();

        homePage.goToCartPage();
        softAssert.assertEquals(productName, cartPage.getCartProductNameByIndex(1),"Product Name in cart doesn't match the name in Product details page");

        cartPage.deleteProductFromCartByName(productName);
        softAssert.assertTrue(cartPage.isProductDeletedFromCart(productName),"Product isn't deleted from the cart");
       // softAssert.assertEquals(cartPage.isProductDeletedFromCart2(productName),0);

        softAssert.assertAll();
    }

    @Test(priority = 3,description = "Verify successful checkout for a random item after login")
    public void verifySuccessfulCheckout(Method method){
        startTest(method.getName(), "Verify successful checkout for a random item after login");

        homePage.goToLoginModal();
        homePage.login(getExcelValueByAttribute("Credentials","username"),
                getExcelValueByAttribute("Credentials","password"));

        Assert.assertEquals(homePage.getUsernameAfterLogin(),"Welcome "+ homePage.getUsername());

        //homePage.clickOnCategory("Phones");
        categoryPage.clickOnRandomItem();
        softAssert.assertTrue(productPage.isAddToCartDisplayed());
        String productName =  productPage.getProductName();

        productPage.clickOnAddToCart();
        softAssert.assertEquals(productPage.getAddToCartSuccessMsg(),"Product added.");
        productPage.acceptAlert();

        homePage.goToCartPage();
        softAssert.assertEquals(productName, cartPage.getCartProductNameByIndex(1),"Product Name in cart doesn't match the name in Product details page");

        cartPage.clickOnPlaceOrder();

        cartPage.insertPurchaseDetails((getExcelValueByAttribute("Payment","Name")),
                getExcelValueByAttribute("Payment","Country"),
                getExcelValueByAttribute("Payment","City"),
                getExcelValueByAttribute("Payment","CardNumber"),
                getExcelValueByAttribute("Payment","Month"),
                getExcelValueByAttribute("Payment","Year"));

        softAssert.assertTrue(cartPage.isSuccessfulPurchaseDisplayed());
        softAssert.assertAll();
    }

}
