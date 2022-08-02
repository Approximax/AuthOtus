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

    @FindBy(xpath = "//button[@data-modal-id='new-log-reg']")
    private WebElement loginButton;

//    поменять селектор
    @FindBy(css = "input[class ^=new-input]:first")
    private WebElement loginField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passField;

//    поменять селектор
    @FindBy(xpath = "//button[text() = 'Войти']")
    private WebElement enterButton;

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