package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends PageBase{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartProducts = By.cssSelector("[class=\"success\"]");
    private By productName = By.xpath("//*[@class=\"success\"]/td)[2]");
    private By deleteBtn = By.xpath("//a[contains(@onclick,\"deleteItem\")]");
    private By totalCartPrice = By.id("totalp");
    private By placeOrderBtn = By.cssSelector("[data-target=\"#orderModal\"]");

    //Place Order Modal
    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By cardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private By purchaseBtn = By.cssSelector("button[onclick=\"purchaseOrder()\"]");

    private By purchaseNotification = By.xpath("//div[contains(@class,\"sweet-alert\")]");






    public List<WebElement> cartProducts(){
        return driver.findElements(cartProducts);
    }

    //index starting from 1
    public String getCartProductNameByIndex(int cartProductOrder){
        return getElementText(By.xpath("(//tr[@class=\"success\"]["+cartProductOrder+"]//td)[2]"));
        //return getElementText(cartProducts().get(cartProductOrder).findElement(By.xpath("(//td)[2]")));
        //return getElementText(cartProducts().get(cartProductOrder).findElement(By.xpath("(//*[@class=\"success\"]/td)[2]")));
    }
/*
    public String getProductPriceByName(String productName){
        return getElementText(driver.findElement(RelativeLocator.with(productPrice).toRightOf(By.xpath("//td[contains(text(),'"+productName+"')]"))));
        //return getElementText(driver.findElement(cartProducts).findElement(By.xpath("//td[text()=\""+productName+"\"]")));
        //return getElementText(driver.findElement(By.xpath("//*[@class=\"success\"]/td[text()=\""+productName+"\"]")));
    }*/

    public void deleteProductFromCartByName(String productName){
        waitClick(driver.findElement(RelativeLocator.with(deleteBtn).toRightOf(By.xpath("//td[contains(text(),'"+productName+"')]"))));
    }

    public boolean isProductDeletedFromCart(String productName){
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//td[text()=\""+productName+"\"]"))));
        return driver.findElements(By.xpath("//td[text()=\""+productName+"\"]")).isEmpty();
         }

    public int isProductDeletedFromCart2(String productName){
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("login2"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("login2"))));
        wait.until(ExpectedConditions.attributeToBe(By.id("logout2"),"style","display: block;"));
        return driver.findElements(By.xpath("//td[text()=\""+productName+"\"]")).size();
    }

    public void clickOnPlaceOrder(){
        waitClick(placeOrderBtn);
    }

    public void insertPurchaseDetails(String name, String country, String city, String cardNumber, String month, String year){
        insertText(nameField,name);
        insertText(countryField, country);
        insertText(cityField, city);
        insertText(cardField, cardNumber);
        insertText(monthField, month);
        insertText(yearField, year);
        waitClick(purchaseBtn);
    }

    public boolean isSuccessfulPurchaseDisplayed(){
        return isElementDisplayed(purchaseNotification);
    }

}
