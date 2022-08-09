import PageObjects.AuthPage;
import PageObjects.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

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

//    Пришлось вынести сюда, иначе не вычитывалось корректно и было слишком много ненужного кода
    @FindBy(xpath = "//input [@name = 'contact-0-value']")
    private WebElement contactField1;

    @FindBy(xpath = "//input [@name = 'contact-1-value']")
    private WebElement contactField2;


    @org.junit.Test
    public void ContactsSaveTest () {

        String text1 = "123";
        String text2 = "456";

        driver.get("https://otus.ru");

        AuthPage authPage = new AuthPage(driver);
        authPage.loginEnter();
        authPage.inputLogin(login);
        authPage.inputPass(password);
        authPage.clickEnterButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.enterLK();
        profilePage.enterContacts(contactField1,text1).enterContacts(contactField2, text2).saveData();

        driver.manage().deleteAllCookies();
        driver.get("https://otus.ru");
        authPage.loginEnter();
        authPage.inputLogin(login);
        authPage.inputPass(password);
        authPage.clickEnterButton();

        String actual1;
        String actual2;

//        Assert.assertEquals("123", actual1.getEle);
    }

    public String getElementText (WebElement element) {
        return element.getText();
    }
}