package br.com.selenium.excption;

public class ArquiteturaExption extends RuntimeException {
    public ArquiteturaExption(String msg) {
        super(msg);
    }

    public ArquiteturaExption(String msg, Throwable causa) {
        super(msg, causa);
    }
}