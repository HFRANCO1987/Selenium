package br.com.selenium.excption;

public class LoginException extends RuntimeException {

    public LoginException(String msg) {
        super(msg);
    }

    public LoginException(String msg, Throwable causa) {
        super(msg, causa);
    }
}