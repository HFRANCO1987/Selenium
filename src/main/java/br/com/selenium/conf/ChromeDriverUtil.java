package br.com.selenium.conf;

import br.com.selenium.excption.ArquiteturaExption;
import br.com.selenium.excption.LoginException;
import java.util.List;
import java.util.ResourceBundle;
import org.junit.Assert;
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
    private String mensagemErro = bundle.getString("message.erro.inesperado");
    private static boolean logged = false;
    private static final String CARACTERES = "[^\\\\p{ASCII}]";
    private static PropertiesLoader loader = new PropertiesLoader();

    static {
        System.setProperty("webdriver.chrome.driver", System.getProperties().getProperty("user.dir") + "/" + arquiteturaPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1440,900");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
//        driver.manage().timeouts().implicitlyWait(Config.DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        Config.TOP_URL = (String) loader.getValor("url");
    }

    private static String arquiteturaPath() {
        String property = System.getProperties().getProperty("os.arch");
        if (property.equals("amd64")) {
            return Config.WEB_DRIVER_PATH_64;
        }
        if (property.equals("i386")) {
            return Config.WEB_DRIVER_PATH_32;
        }
        throw new ArquiteturaExption("Arquitetura não identificada.");
    }

    public void login() {
        try {
            driver.navigate().to(Config.TOP_URL);
            navigateTo(Config.TOP_URL + "/salute/pages/principal.faces");
            driver.findElement(By.id("identificador")).sendKeys(Config.LOGIN);
            driver.findElement(By.id("senha")).sendKeys(Config.SENHA);
            driver.findElement(By.id("buttonEntrar")).click();
            this.logged = true;
        } catch (Exception e) {
            throw new LoginException("Pagina de Login não encontrada.");
        }
    }

    public boolean estaLogged() {
        return this.logged;
    }

    public void logout() {
        driver.findElement(By.id("logout")).click();
    }

    public void navigateTo(String string) {
        try {
            driver.navigate().to(string);
        } catch (Exception e) {
            throw new LoginException("Impossivel acessar URL " + string);
        }
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
        if (aguarde().equals(Boolean.TRUE)) {
            this.findElementById(id).click();
        }
    }

    public void click(String id, Integer waitInSecond) {
        this.findElementById(id).click();
        waitInSecond(waitInSecond);
    }

    public void write(String id, String value) {
        if (aguarde().equals(Boolean.TRUE)) {
            String text = null;
            while (!value.equals(text)) {
                this.findElementById(id).clear();
                this.findElementById(id).click();
                this.findElementById(id).sendKeys(value);
                text = ((String) executeJS("return document.getElementById('"+id+"').value"));
            }
        }
    }

    public void write(String id, String value, Integer waitInSecond) {
        this.findElementById(id).click();
        this.findElementById(id).sendKeys(value);
    }

    public void writeInputMack(String id, String value, Integer waitInSecond) {
        this.findElementById(id).click();
        this.findElementById(id).sendKeys(value);
        waitInSecond(waitInSecond);
    }

    public void writeAutoComplete(String id, String value) {
        write(id, value, 4);
    }

    public void selectComboBox(String ID, String valueToSelect, Integer waitInSecond) {
        Select selectBox = new Select(findElementById(ID));
        List<WebElement> selected = selectBox.getOptions();
        for (WebElement w : selected) {
            if (!w.getText().isEmpty() && w.getText().trim().equalsIgnoreCase(valueToSelect)) {
                w.click();
            }
        }
        waitInSecond(waitInSecond);
    }

    public void selectComboBox(String id, String valueToSelect) {
        if (aguarde().equals(Boolean.TRUE)) {
            this.findElementById(id).click();
            Select selectBox = new Select(findElementById(id));
            List<WebElement> selected = selectBox.getOptions();
            for (WebElement w : selected) {
                if (w.getText().equalsIgnoreCase(valueToSelect)) {
                    w.click();
                    break;
                }
            }
        }
    }

    public Source selectAutoComplete(String id, String palavra, int index) throws Exception {
        try {
            Source source = new Source();
            if (aguarde().equals(Boolean.TRUE)) {
                WebElement element = driver.findElement(By.id(id));
                WebElement findElement = element.findElement(By.tagName("input"));
                findElement.clear();
                findElement.click();
                findElement.sendKeys(palavra);
                WebElement findElement1 = driver.findElement(By.id(id + "_panel"));
                while (true) {
                    if (findElement1.isDisplayed()) {
                        WebElement table = findElement1.findElement(By.tagName("table"));
                        WebElement tbody = table.findElement(By.tagName("tbody"));
                        List<WebElement> trList = tbody.findElements(By.tagName("tr"));
                        WebElement td = trList.get(0);
                        String descricao = td.getText();
                        source.put("descricao", descricao);
                        td.click();
                        break;
                    }
                }
                WebElement dialogStatus = driver.findElement(By.id("dialogStatus"));
                while (true) {
                    String cssValue = dialogStatus.getCssValue("visibility");
                    if (cssValue.equals("hidden")) {
                        break;
                    }
                }
            }
            return source;
        } catch (Exception e) {
            throw e;
        }
    }

    private Boolean aguarde() {
        Boolean result = Boolean.FALSE;
        WebElement dialogStatus = driver.findElement(By.id("dialogStatus"));
        while (true) {
            String cssValue = dialogStatus.getCssValue("visibility");
            if (cssValue.equals("hidden")) {
                result = Boolean.TRUE;
                break;
            }
        }
        return result;
    }

    public Source selectAutoComplete(String id, String palavra, int index, Integer wait) throws Exception {
        try {
            Source source = new Source();
            if (aguarde().equals(Boolean.TRUE)) {
                WebElement element = driver.findElement(By.id(id));
                WebElement findElement = element.findElement(By.tagName("input"));
                findElement.clear();
                findElement.click();
                findElement.sendKeys(palavra);
                WebElement findElement1 = driver.findElement(By.id(id + "_panel"));
                while (true) {
                    if (findElement1.isDisplayed()) {
                        WebElement table = findElement1.findElement(By.tagName("table"));
                        WebElement tbody = table.findElement(By.tagName("tbody"));
                        List<WebElement> trList = tbody.findElements(By.tagName("tr"));
                        WebElement td = trList.get(0);
                        String descricao = td.getText();
                        source.put("descricao", descricao);
                        td.click();
                        break;
                    }
                }
                WebElement dialogStatus = driver.findElement(By.id("dialogStatus"));
                while (true) {
                    String cssValue = dialogStatus.getCssValue("visibility");
                    if (cssValue.equals("hidden")) {
                        break;
                    }
                }
            }
            return source;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @autor: Alci Barros
     * @date: 30/09/2013
     */
    public void selectAutoCompleteA(String id, String palavra, int index, Integer wait) {
        executeJS("document.getElementById('" + id + "_input').value =''");
        this.findElementById(id + "_input").click();
        this.findElementById(id + "_input").sendKeys(palavra);
        while (true) {
            try {
                WebElement panel = findElementById(id + "_panel");
                WebElement tableList = panel.findElement(By.tagName("table"));
                WebElement element = tableList.findElements(By.tagName("tbody")).get(index);
                element.click();
                break;
            } catch (Exception e) {
            }
        }
        while (true) {
            Boolean executeJS = (Boolean) executeJS("return (document.getElementById('dialogStatus').style.visibility) === 'visible'");
            if (executeJS) {
                break;
            }
        }
        waitInSecond(wait);
    }

    public void selectAutoCompleteB(String id, String palavra, int index, Integer wait) {
        executeJS("document.getElementById('" + id + "_input').value =''");
        this.findElementById(id + "_input").click();
        this.findElementById(id + "_input").sendKeys(palavra);
        while (true) {
            try {
                WebElement panel = findElementById(id + "_panel");
                WebElement tableList = panel.findElement(By.tagName("table"));
                WebElement element = tableList.findElements(By.tagName("tbody")).get(index);
                element.click();
                break;
            } catch (Exception e) {
            }
        }
        while (true) {
            Boolean executeJS = (Boolean) executeJS("return (document.getElementById('dialogStatus').style.visibility) === 'visible'");
            if (executeJS) {
                break;
            }
        }
        waitInSecond(wait);
    }

//    public void selectAutoComplete(String id, String palavra, int index, Integer wait) {
//
//        write(id + "_input", palavra, wait);
//        WebElement panel = findElementById(id + "_panel");
//        waitInSecond(wait);
//        try {
//            WebElement ulList = panel.findElement(By.tagName("ul"));
//            if (ulList != null) {
//                WebElement element = ulList.findElements(By.tagName("li")).get(index);
//                element.click();
//                waitInSecond(wait);
//            }
//        } catch (Exception e) {
//            try {
//                WebElement tableList = panel.findElement(By.tagName("table"));
//                if (tableList != null) {
//                    WebElement element = tableList.findElements(By.tagName("tbody")).get(index);
//                    element.click();
//                    waitInSecond(wait);
//                }
//            } catch (Exception ex) {
//                this.selectAutoComplete(id, palavra, index, wait);
//            }
//        }
//    }
    public String getGrowlMessage() {
        String msg = null;
        for (int i = 0; i < 5; i++) {
            waitInSecond(2);
            msg = message();
            if (!msg.equals("")) {
                break;
            }
        }
        return msg;
    }

    private String message() {
        try {
            WebElement findElement = driver.findElement(By.id("messages_container"));
            findElement = findElement.findElement(By.className("ui-growl-message"));
            String text = findElement.findElement(By.tagName("p")).getText();
            return text;
        } catch (Exception e) {
            return "";
        }
    }

    public int assertMessageIs(String msg) {
        String a = getGrowlMessage().trim();
        String b = bundle.getString(msg).trim();
        System.out.println(a);
        return (a.equals(b)) ? 1 : (a.equals(mensagemErro) ? 0 : -1);
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

    public WebElement waitUntilElementExistsAndGet(String s) {
        return findElementById(s);
    }

    public void selectAutoCompleteListElementTable(String s, int i, int j) {
    }

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

    public int botaoSalvar(String id, String msg) {
        int a = -1;
        if (aguarde().equals(Boolean.TRUE)) {
            this.findElementById(id).click();
            a = assertMessageIs(msg);
        }
        return a;
    }

    public int botaoSalvar(String id, String msg, Integer waitInSecond) {
        int a = -1;
        if (aguarde().equals(Boolean.TRUE)) {
            this.findElementById(id).click();
            a = assertMessageIs(msg);
        }
        return a;
    }

    public void assertMessagens(int a) {
        switch (a) {
            case 1:
                System.out.println(" - Sucesso...");
                Assert.assertTrue(true);
                break;
            case 0:
                System.out.println(" - Erro...");
                Assert.assertTrue(false);
                break;
            case -1:
                System.out.println(" - Alerta...");
                Assert.assertTrue(false);
                break;
        }
    }

    public void selectOption(String ID, int index, Integer wait) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@id=\'");
        stringBuilder.append(ID);
        stringBuilder.append(""
                + "\']/tbody/tr/td[");
        stringBuilder.append(index);
        stringBuilder.append("]");
        driver.findElement(By.xpath(stringBuilder.toString())).click();

        waitInSecond(wait);
    }

    public void selectOneOption(String ID, int index, Integer wait) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@id=\'");
        stringBuilder.append(ID);
        stringBuilder.append(""
                + "\']/tbody/tr[");
        stringBuilder.append(index);
        stringBuilder.append("]");
        stringBuilder.append("/td[1]");

        driver.findElement(By.xpath(stringBuilder.toString())).click();
        waitInSecond(wait);
    }

    public void clickTab(String ID, int index, Integer wait) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@id=\"");
        stringBuilder.append(ID);
        stringBuilder.append(""
                + "\"]/ul/li[");
        stringBuilder.append(index);
        stringBuilder.append("]/a");
        driver.findElement(By.xpath(stringBuilder.toString())).click();

        waitInSecond(wait);
    }

    public void clickPlusTab(String ID, Integer wait) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@id=\"");
        stringBuilder.append(ID);
        stringBuilder.append(""
                + "\"]/legend/span");
        driver.findElement(By.xpath(stringBuilder.toString())).click();
    }

    public void closePopUp(String ID, int index, Integer wait) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//*[@id=\"");
        stringBuilder.append(ID);
        stringBuilder.append(""
                + "\"]/div[");
        stringBuilder.append(index);
        stringBuilder.append("]/a/span");
        driver.findElement(By.xpath(stringBuilder.toString())).click();
    }

}
