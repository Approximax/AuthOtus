package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

//    В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
//    Нажать сохранить
    WebDriver driver;
    Actions actions;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//p [contains(@class, 'text__username')]")
    private WebElement profileMenu;

    @FindBy(xpath = "//b [contains(@class, 'dropdown-text_name')]")
    private WebElement profileLink;

    @FindBy(xpath = "//button [text() = 'Добавить']")
    private WebElement addBtn;

    @FindBy(xpath = "//button [@title = 'Сохранить и продолжить']")
    private WebElement saveBtn;


    public void enterLK () {
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
        actions.moveToElement(profileMenu);
        profileLink.click();
    }

    public ProfilePage enterContacts (WebElement element, String text) {
        addBtn.click();
        element.sendKeys(text);
        return new ProfilePage(driver);
    }

    public void saveData () {
        saveBtn.click();
    }

    public String getElementText (WebElement element) {
        return  element.getText();
    }
}