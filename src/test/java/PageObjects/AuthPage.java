package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    public WebDriver driver;


    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button [@data-modal-id = 'new-log-reg']")
    private WebElement loginEnterButton;

    @FindBy(xpath = "//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passField;

    @FindBy(xpath = "//div [contains(@class,'new-input-line_relative')]/child::button")
    private WebElement enterButton;

    public void loginEnter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.elementToBeClickable(loginEnterButton)).click();
    }

    public void clickEnterButton() {
        enterButton.submit();
    }

    public AuthPage userAuth (String login, String password) {
        loginEnter();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")));
        driver.findElement(By.xpath("//input[@name = 'email' and @type = 'text' and not (@class = 'hide')]")).sendKeys(login);
        passField.sendKeys(password);
        clickEnterButton();
        return new AuthPage(driver);
    }

}