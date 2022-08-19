import page_objects.AuthPage;
import page_objects.ProfilePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import page_objects.WebDriverFactory;

public class SaveContactsTest {
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");


    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @Before
    public void SetUp() {
        WebDriverFactory.create("chrome");
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

        driver.get(System.getProperty("base.url"));

        AuthPage authPage = new AuthPage(driver);
        authPage.userAuth("soremec508@5k2u.com", "Qwerty123-");
        logger.info("Авторизация пройдена");

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
        profilePage.enterContacts2contacts(text1, text2).saveData();
        logger.info("Контакты заполнены");

        driver.manage().deleteAllCookies();
        driver.get(url);
        logger.info("Открыт чистый браузер");

        authPage.userAuth(System.getProperty("login"), System.getProperty("password"));
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