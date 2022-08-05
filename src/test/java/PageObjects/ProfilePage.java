package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

//    В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
//    Нажать сохранить
    WebDriver driver;
    Actions actions;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//p [contains(@class, 'text__username')]")
    private WebElement profileMenu;

    @FindBy(xpath = "//b [contains(@class, 'dropdown-text_name')]")
    private WebElement profileLink;

//    @FindBy(xpath = "//input [@data-title = 'Фамилия']")
//    private WebElement userSurname;

    @FindBy(xpath = "//button [text() = 'Добавить']")
    private WebElement addBtn;

    @FindBy(xpath = "//input [@name = 'contact-0-value']")
    private WebElement contactField1;

    @FindBy(xpath = "//input [@name = 'contact-1-value']")
    private WebElement contactField2;

    @FindBy(xpath = "//button [@title = 'Сохранить и продолжить']")
    private WebElement saveBtn;


    public void enterLK () {
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

//    public void enter2Contacts () {
//        addBtn.click();
//        contactField1.sendKeys(tex);
//    }

}