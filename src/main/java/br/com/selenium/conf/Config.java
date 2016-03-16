package br.com.selenium.conf;

public class Config {

    //Linux 32 bits
    public static final String WEB_DRIVER_PATH_32 = "chromedriver32";

    //Linux 64 bits
    public static final String WEB_DRIVER_PATH_64 = "chromedriver64";

        //Windows
    //public static final String WEB_DRIVER_PATH = "chromedriver32.exe";
    public static String TOP_URL;

        //public static final String TOP_URL = "http://localhost:8080";
    public static final String LOGIN = "joseselenium";
    public static final String SENHA = "ewq321";

    public static final long DEFAULT_TIMEOUT_SECONDS = 10;

    public static final long DEFAULT_WAIT = 1000l;
}
