import com.thoughtworks.gauge.*;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    String url = "https://todomvc.com/examples/vue/";

    @BeforeSuite
    public void senaryoOncesi() throws InterruptedException {
        System.out.println(convertTurkishChar("Senaryo Başladı"));
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().window().maximize();
        System.out.println(convertTurkishChar("Driver Çalıştı"));
    }

    static WebDriver driver;
    static Actions action;

    @AfterSuite
    public void senaryoSonrasi() {
        driver.quit();
        System.out.println(convertTurkishChar("Senaryo Sonlandı"));
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }

    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public void clickListElement(String by,int index){
        findElements(by).get(index).click();
    }

    public void enterClick(String by){
        findElement(by).sendKeys(Keys.ENTER);
    }

    public  void sendkeysElement(String by,String text){
        findElement(by).sendKeys(text);
    }

    public void assertControls(String assertName, String expectedName){
        List<String> list = textList(assertName);
        Assert.assertTrue("Element var"+expectedName,list.contains(expectedName));
    }

    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }

    public List<String> textList(String key){
        List<WebElement> todoList = findElements(key);
        List<String> list = new ArrayList<>();
        for (WebElement i: todoList) {
            list.add(i.getText());
        }
        return list;
    }

    public  boolean markControlBase(String item, String toDoCompleted, String itemControl){

        List<WebElement> todoList = findElement(toDoCompleted).findElements(By.tagName("li"));
        String textmark = todoList.get(toDoIndex(item,itemControl)).getAttribute("class");
        String markE = "todo completed";
        return (textmark.equals(markE));
    }

    public int toDoIndex(String item,String itemControl){

        return textList(itemControl).indexOf(item);
    }

    public void listButtonClick(String item, String button, String itemControl){

        List<String> list = textList(itemControl);
        for (int i = 0; i < list.size(); i++) {
            if (item.equals(list.get(i))) {
                clickListElement(button, i);
                System.out.println(button+" = Elemente Tiklandi");
            }
        }
    }
}