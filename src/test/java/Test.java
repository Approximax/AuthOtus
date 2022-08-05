import PageObjects.AuthPage;
import PageObjects.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test {
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");
    protected WebDriver driver;
    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void setDown() {
        if(driver!=null)
            driver.quit();
    }

    @org.junit.Test
    public void ContactsSaveTest () {
        driver.get("https://otus.ru");
        By contactField1 = By.xpath("//input [@name = 'contact-0-value']");

        AuthPage authPage = new AuthPage(driver);
        authPage.loginEnter();
        authPage.inputLogin(login);
        authPage.inputPass(password);
        authPage.clickEnterButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
//        profilePage.enterContacts(contactField1,"123");


    }

//    @org.junit.Test
//    public void Test1 (){
//        driver.get("https://otus.ru");
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
//        driver.findElement(By.xpath("//button [@data-modal-id = 'new-log-reg']")).click();
//        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")));
//        driver.findElement(By.xpath("//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")).sendKeys("soremec508@5k2u.com");
//        driver.findElement(By.xpath("//input[@type = 'password']")).sendKeys("Qwerty123-");
//        driver.findElement(By.xpath("//div [contains(@class,'new-input-line_relative')]/child::button")).submit();
//
//        WebElement element = driver.findElement(By.xpath("//b[text() = 'Иван']"));
//        new Actions(driver)
//                .moveToElement(element)
//                .click();
//
//
//    }
}