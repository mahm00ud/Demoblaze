package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageBase {

    public HomePage(WebDriver driver) {super(driver);}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    //Variables
    private String username;
    private String password;

    //HeaderSectionElements
    private By headerSignupBtn = By.id("signin2");
    private By headerloginBtn = By.id("login2");
    private By headerCartBtn = By.id("cartur");
    private By welcomeUser = By.id("nameofuser");

    //Signup Modal
    private By signupUsernameField = By.id("sign-username");
    private By signupPasswordField = By.id("sign-password");
    private By signupBtn = By.cssSelector("button[onclick=\"register()\"]");

    //Login Modal
    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginBtn = By.cssSelector("button[onclick=\"logIn()\"]");

    //Categories Modal
    private By categoriesList = By.cssSelector("[class=\"list-group\"]");

    //Actions
    public void goToSignUpModal(){
        waitClick(headerSignupBtn);
    }

    public void goToLoginModal(){
        waitClick(headerloginBtn);
    }

    public void goToCartPage(){
        waitClick(headerCartBtn);
    }

    public void register(String username, String password){
        String localUsername = username;
        String localPassword = password;

        insertText(signupUsernameField, localUsername);
        insertText(signupPasswordField, localPassword);
        waitClick(signupBtn);

        if (getAlertText().equalsIgnoreCase("This user already exist.")){
            acceptAlert();
            localUsername = localUsername + generateRandomNumber(10);
            register(localUsername, password);
        } else if (getAlertText().equalsIgnoreCase("Sign up successful.")) {
            this.username = localUsername;
            this.password = localPassword;
        }
    }

    public void loginAfterRegisteration(){
        insertText(loginUsernameField,this.username);
        insertText(loginPasswordField,this.password);
        waitClick(loginBtn);
    }

    public void login(String username, String password){
        insertText(loginUsernameField, username);
        insertText(loginPasswordField, password);
        waitClick(loginBtn);

        this.username = username;
        this.password = password;
    }

    public String getUsernameAfterLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
        return driver.findElement(welcomeUser).getText();
    }

    public void clickOnCategory(String categoryName){
        By category = By.xpath("//a[@id='itemc' and text()=\"" +categoryName+ "\"]");
        waitClick(category);
        if (categoryName == "Phones") {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"card h-100\"]//a[text()=\"Samsung galaxy s6\"])[1]")));
        } else if (categoryName == "Laptops") {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"card h-100\"]//a[text()=\"Sony vaio i5\"])[1]")));
        } else if (categoryName == "Monitors") {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"card h-100\"]//a[text()=\"Apple monitor 24\"])[1]")));
    }else {
            System.out.println("Can't find category identifier");
        }
        }

}