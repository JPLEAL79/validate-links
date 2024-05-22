package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordField;
    @FindBy(xpath = "//*[@id='login-button']")
    public WebElement loginButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(this.driver, this);

    }

    public void Login(String email, String password) {

        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

    }
}
