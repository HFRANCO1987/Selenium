package br.com.selenium.conf;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ChromeDriverUtil {

    public static WebDriver driver;
    private static final String JQUERY_ACTIVE_CONNECTIONS_QUERY = "return $.active == 0;";
    private ResourceBundle bundle = ResourceBundle.getBundle("Resources");
    private boolean logged = false;

    static {
        System.setProperty("webdriver.chrome.driver", System.getProperties().getProperty("user.dir") + "/" + Config.WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1440,900");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        //driver.manage().timeouts().implicitlyWait(Config.DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public void login() {
        driver.navigate().to(Config.TOP_URL);
        navigateTo(Config.TOP_URL + "/salute/pages/principal.faces");
        driver.findElement(By.id("identificador")).sendKeys(Config.LOGIN);
        driver.findElement(By.id("senha")).sendKeys(Config.SENHA);
        driver.findElement(By.id("buttonEntrar")).click();
        this.logged = true;
    }

    public boolean isLogged() {
        return this.logged;
    }

    public void logout() {
        driver.findElement(By.id("logout")).click();
    }

    public void navigateTo(String string) {
        driver.navigate().to(string);
    }

    public WebElement findElementBy(By by) {
        return driver.findElement(by);
    }

    public WebElement findElementById(String elementId) {
        return driver.findElement(By.id(elementId));
    }

    public void waitInSecond(Integer waitInSecond) {
        if (waitInSecond != null) {
            try {
                Thread.sleep(waitInSecond * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void click(String id) {
        this.click(id, 2);
    }

    public void click(String id, Integer waitInSecond) {
        this.findElementById(id).click();
        waitInSecond(waitInSecond);
    }

    public void write(String id, String value) {
        write(id, value, 1);
    }

    public void write(String id, String value, Integer waitInSecond) {
        if (this.findElementById(id).getAttribute("value").equals("")) {
            this.findElementById(id).sendKeys(value);
            //waitInSecond(waitInSecond);
        }
    }

    public void writeAutoComplete(String id, String value) {
        write(id, value, 4);
    }

    public WebElement selectComboBox(String ID, String valueToSelect) {
        Select selectBox = new Select(findElementById(ID));
        List<WebElement> selected = selectBox.getOptions();
        for (WebElement w : selected) {
            if (!w.getText().isEmpty() && w.getText().trim().equalsIgnoreCase(valueToSelect)) {
                w.click();
                return w;
            }
        }
        return null;
    }

    public void selectAutoComplete(String id, String palavra, int index) throws InterruptedException {
        selectAutoComplete(id, palavra, index, 3);
    }

    public void selectAutoComplete(String id, String palavra, int index, Integer wait) throws InterruptedException {

        write(id + "_input", palavra, wait);
        WebElement panel = findElementById(id + "_panel");

        try {
            WebElement ulList = panel.findElement(By.tagName("ul"));
            if (ulList != null) {
                WebElement element = ulList.findElements(By.tagName("li")).get(index);
                element.click();
                waitInSecond(wait);
            }
        } catch (Exception e) {
            try {
                WebElement tableList = panel.findElement(By.tagName("table"));
                if (tableList != null) {
                    WebElement element = tableList.findElements(By.tagName("tbody")).get(index);
                    element.click();
                    waitInSecond(wait);
                }
            } catch (Exception ex) {
                this.selectAutoComplete(id, palavra, index, wait);
            }
        }
    }

    public String getGrowlMessage() {

        //WebElement findElement = driver.findElement(By.className("ui-growl-message"));
        WebElement findElement = driver.findElement(By.id("messages_container"));
        findElement = findElement.findElement(By.className("ui-growl-message"));
        String text = findElement.findElement(By.tagName("p")).getText();
        return text;
    }

    public void assertMessageIs(String msg) {
        String a = getGrowlMessage().toString();
        String b = bundle.getString(msg.trim()).toString();
        Assert.assertEquals(a, b);
        waitInSecond(2);
    }

    public Object executeJS(String js, Object... os) {
        return ((JavascriptExecutor) driver).executeScript(js, os);
    }

    public void quit() {
        driver.quit();
    }

    public void assertContains(String msg) throws InterruptedException {
        Assert.assertTrue(getGrowlMessage().trim().contains(msg));
        waitInSecond(2);
    }

    public boolean pageWasRedirectTo(String page) {
        return driver.getCurrentUrl().contains(page);
    }

    // metodos excluidos. mantidos para evitar erros de compilação
    public WebElement waitUntilElementExistsAndGet(String s) {
        return findElementById(s);
    }

    public void selectAutoCompleteListElementTable(String s, int i, int j) {
    }

//    public void autoComplete(String s, String t, int i, int j) {
//        
//    }
    public void selectAutoCompleteListElement(String s, int i, int j) {
    }

    public void selectAutoCompleteListElementTable(String s, int i) {
    }

    public void selectTable(String id, int index, Integer wait) throws InterruptedException {
        WebElement panel = findElementById(id + "_data");
        List<WebElement> findElements = panel.findElements(By.tagName("tr"));
        if (findElements != null) {
            findElements.get(index).click();
            waitInSecond(wait);
        }
        waitInSecond(wait);
    }

}
