package br.com.selenium.thread;


import br.com.selenium.conf.ChromeDriverUtil;
import java.util.ResourceBundle;

public class VigiaMensagemTheared extends Thread {

    private static ChromeDriverUtil driverUtil;
    private ResourceBundle bundle = ResourceBundle.getBundle("Resources");

    public VigiaMensagemTheared(ChromeDriverUtil driverUtil) {
        VigiaMensagemTheared.driverUtil = driverUtil;
    }

    public void run() {
        String mensagemErro = bundle.getString("message.erro.inesperado");
        String mensagemSucesso = bundle.getString("message.criadocomsucesso");
        while (true) {
            String growlMessage = driverUtil.getGrowlMessage();
            if (growlMessage.equals(mensagemErro)) {
                System.out.println("ERRO ENCONTRADO..........................");
                driverUtil.waitInSecond(2);
            }
            driverUtil.waitInSecond(1);
        }
    }
}
