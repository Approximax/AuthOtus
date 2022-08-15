import PageObjects.AuthPage;
import PageObjects.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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


    @org.junit.Test
    public void ContactsSaveTest () {

        String text1 = "123";
        String text2 = "456";

        driver.get("https://otus.ru");

        AuthPage authPage = new AuthPage(driver);
        authPage.userAuth("soremec508@5k2u.com", "Qwerty123-");
        logger.info("Авторизация пройдена");

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
        profilePage.enterContacts2contacts(text1, text2).saveData();
        logger.info("Контакты заполнены");

        driver.manage().deleteAllCookies();
        driver.get("https://otus.ru");
        logger.info("Открыт чистый браузер");

        authPage.userAuth("soremec508@5k2u.com", "Qwerty123-");
        logger.info("Авторизация пройдена");

        profilePage.enterLK();
        String actual1 = driver.findElement(By.xpath("//input [@name = 'contact-0-value']")).getText();
        logger.info("Данные первого конаткта получены");

        try {
            String actual2 = driver.findElement(By.xpath("//input [@name = 'contact-1-value']")).getText();
            logger.info("Данные второго контакта получены");
            Assert.assertEquals(text2, actual2);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertEquals(text1, actual1);

    }
}