package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProfilePage extends BasePage {

//    В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
//    Нажать сохранить

    @FindBy(xpath = "//p [contains(@class, 'text__username')]/following-sibling::div[@class = 'header2-menu__caret']")
    private WebElement profileMenu;

    @FindBy(xpath = "//b [contains(@class, 'dropdown-text_name')]")
    private WebElement profileLink;

    @FindBy(xpath = "//button [text() = 'Добавить']")
    private WebElement addBtn;

    @FindBy(xpath = "//button [@title = 'Сохранить и продолжить']")
    private WebElement saveBtn;

    @FindBy(xpath = "//input [@name = 'contact-0-value']")
    private WebElement contactField1;

    @FindBy(xpath = "//input [@name = 'contact-1-value']")
    private WebElement contactField2;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }


    public void enterLK () {
        wait.until(ExpectedConditions.elementToBeClickable(profileMenu));
        Actions actions = new Actions(driver);
        actions.moveToElement(profileMenu).pause(Duration.ofSeconds(5));
        actions.perform();
        profileLink.click();
    }

    public ProfilePage enterContacts2contacts (String cont1, String cont2) {
        addBtn.click();
        contactField1.sendKeys(cont1);
        contactField2.sendKeys(cont2);
        return new ProfilePage(driver);
    }

    public void saveData () {
        saveBtn.click();
    }

//    public String get2ElementsText (String elemText1, String elemText2) {
//        elemText1 = contactField1.getText();
//        elemText2 = contactField2.getText();
//        return elemText1;
//    }
}