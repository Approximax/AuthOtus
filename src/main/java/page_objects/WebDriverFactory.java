package page_objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverFactory {


//    Создайте класс page_objects.WebDriverFactory со статическим методом create();
//    Метод create() принимает обязательный параметр webDriverName и необязтельный параметр options, а возвращает соответствующий имени вебдрайвер с заданными (если были) options
//    Примеры использования
//    WebDriver wd = page_objects.WebDriverFactory.createNewDriver("chrome");
//    или
//    FirefoxOptions options = new FirefoxOptions();
//    WebDriver wd = page_objects.WebDriverFactory.createNewDriver("firefox", options);

    public static WebDriver create(String webDriverName){
        return create(webDriverName, null);
    }

    public static WebDriver create(String webDriverName, WebDriver.Options options){
        Browser browser = Browser.valueOf(webDriverName.toUpperCase());
        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                return new ChromeDriver(chromeOptions.addArguments(options));
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                return new FirefoxDriver(firefoxOptions.addArguments(options));
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
//                в классе опций сафари драйвера из библиотеки силениума нет метода добавления аргумета с опциями
                return new SafariDriver(safariOptions);
            case IE:
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                return new InternetExplorerDriver();
        }
        throw new IllegalArgumentException("Некорректно заданн аргумент");
    }

    protected enum Browser{
        CHROME,
        FIREFOX,
        SAFARI,
        IE
    }
}


