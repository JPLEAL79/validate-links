package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    public WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        //instrucciones para arrancar el browser
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--disable-notifications"); //Opción de Chrome para desactivar notificacion
        opts.addArguments("--start-maximized"); //Opción de Chrome para que inicie maximizado
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
