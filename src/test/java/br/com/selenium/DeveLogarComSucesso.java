package br.com.selenium;

import br.com.selenium.conf.ChromeDriverUtil;
import static org.junit.Assert.*;
import org.junit.Test;

public class DeveLogarComSucesso {

    private static final ChromeDriverUtil driverUtil = new ChromeDriverUtil();

    @Test
    public void deveLogarComSucesso() {
        driverUtil.login();
        assertTrue(driverUtil.driver.getCurrentUrl().endsWith("principal.faces"));
        driverUtil.logout();
        assertFalse(driverUtil.driver.getCurrentUrl().endsWith("principal.faces"));
        driverUtil.quit();
    }
}
