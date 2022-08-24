import org.openqa.selenium.WebDriver;
import page_objects.AuthPage;
import page_objects.ProfilePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import page_objects.WebDriverFactory;

import java.io.IOException;

public class SaveContactsTest {

    protected WebDriver driver;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @Before
    public void SetUp() {
        driver = WebDriverFactory.create("chrome");
        logger.info("Драйвер поднят");
    }

    @After
    public void setDown() {
        if(driver!=null)
            driver.quit();
        logger.info("Драйвер закрыт");
    }

    @org.junit.Test
    public void ContactsSaveTest () throws IOException {

        PropReader propReader = new PropReader();

        String text1 = "123";
        String text2 = "456";

        driver.get(propReader.getProp("base.url"));

        AuthPage authPage = new AuthPage(driver);
        authPage.userAuth(propReader.getProp("login"), propReader.getProp("password"));
        logger.info("Авторизация пройдена");

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
        profilePage.enterContacts2contacts(text1, text2).saveData();
        logger.info("Контакты заполнены");

        driver.get(propReader.getProp("base.url"));
        logger.info("Открыт чистый браузер");

        authPage.userAuth(propReader.getProp("login"), propReader.getProp("password"));
        logger.info("Авторизация пройдена");

        profilePage.enterLK();

        Assert.assertTrue(profilePage.checkContactEquivalent(text1));

        Assert.assertTrue(profilePage.checkContactEquivalent(text2));

    }
}