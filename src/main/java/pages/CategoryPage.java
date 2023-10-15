package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoryPage extends PageBase {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    //Items Section
    private By itemsContainer = By.id("tbodyid");
    private By items = By.cssSelector("div[class=\"col-lg-4 col-md-6 mb-4\"]");

    public List<WebElement> getAllItems(){
        return driver.findElements(items);
    }

    public int countItems(){
        return getAllItems().size();
    }

    public void clickOnRandomItem(){
        int itemsCount = countItems();
        WebElement  randomItem = getAllItems().get(generateRandomNumber(itemsCount));
        waitClick(randomItem);
    }
}
