package PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    public WebDriver driver;
    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button [@data-modal-id = 'new-log-reg']")
    private WebElement loginEneterButton;

    @FindBy(xpath = "//button[@data-modal-id='new-log-reg']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passField;

    @FindBy(xpath = "//div [contains(@class,'new-input-line_relative')]/child::button")
    private WebElement enterButton;

    public void loginEnter() {
        loginEneterButton.click();
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPass(String password) {
        passField.sendKeys(password);
    }

    public void clickEnterButton() {
        enterButton.click();
    }

}