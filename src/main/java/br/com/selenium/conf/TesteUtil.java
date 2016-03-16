package br.com.selenium.conf;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TesteUtil {

    protected static ChromeDriverUtil driverUtil = new ChromeDriverUtil();
    protected static Integer TEMPO = 3;

    @BeforeClass
    public static void init() {
        if (!driverUtil.estaLogged()) {
            driverUtil.login();
        }
    }

    @AfterClass
    public static void destry() {

    }

}
