package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class PageBase {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    Alert alert;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }
    public void insertText(By locator, String text){
        WebElement webElement = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        webElement.sendKeys(text);
    }
    public void waitClick(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator))).click();
    }

    public void waitClick(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public boolean isElementDisplayed(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
    }

    public String getElementText(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    public String getElementText(WebElement webElement){
        return wait.until(ExpectedConditions.visibilityOf(webElement)).getText();
    }


    public Alert waitForAlertToBeDisplayed(){
        return alert = wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText(){
        return waitForAlertToBeDisplayed().getText();
    }

    public void acceptAlert(){
        waitForAlertToBeDisplayed().accept();
    }

    public void cancelAlert(){
        waitForAlertToBeDisplayed().dismiss();
    }

    public int generateRandomNumber(int maximumNo){
        Random random = new Random();
        return random.nextInt(maximumNo);
    }
}
