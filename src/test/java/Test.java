import PageObjects.AuthPage;
import PageObjects.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Test {
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");
    protected WebDriver driver;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @Before
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @After
    public void setDown() {
        if(driver!=null)
            driver.quit();
        logger.info("Драйвер закрыт");
    }

//    Пришлось вынести сюда, иначе не вычитывалось корректно и было слишком много ненужного кода
    @FindBy(xpath = "//input [@name = 'contact-0-value']")
    private WebElement contactField1;

    @FindBy(xpath = "//input [@name = 'contact-1-value']")
    private WebElement contactField2;


    @org.junit.Test
    public void ContactsSaveTest () {

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

        String text1 = "123";
        String text2 = "456";

        driver.get("https://otus.ru");

        AuthPage authPage = new AuthPage(driver);
        authPage.userAuth("soremec508@5k2u.com", "Qwerty123-");
        logger.info("Авторизация пройдена");

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
        profilePage.enterContacts(contactField1,text1).enterContacts(contactField2, text2).saveData();
        logger.info("Контакты заполнены");

        driver.manage().deleteAllCookies();
        driver.get("https://otus.ru");
        logger.info("Открыт чистый браузер");

        authPage.userAuth("soremec508@5k2u.com", "Qwerty123-");
        logger.info("Авторизация пройдена");

        String actual1 = profilePage.getElementText(contactField1);
        String actual2 = profilePage.getElementText(contactField2);

        Assert.assertEquals(text1, actual1);
        Assert.assertEquals(text2, actual2);
    }
}