package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends PageBase {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By addToCartBtn = By.cssSelector("[class=\"btn btn-success btn-lg\"]");
    private By productName = By.cssSelector("h2[class=\"name\"]");
    private By proudctPrice = By.cssSelector("h3[class=\"price-container\"]");

    public String getProductName(){
        return getElementText(productName);
    }

    public boolean isAddToCartDisplayed(){
        return isElementDisplayed(addToCartBtn);
    }

    public void clickOnAddToCart(){
        waitClick(addToCartBtn);
    }

    public String getAddToCartSuccessMsg(){
        return getAlertText();
    }

    public String getProductPrice(){
        return getElementText(proudctPrice);
    }

}
