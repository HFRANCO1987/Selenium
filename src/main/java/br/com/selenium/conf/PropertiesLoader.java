package br.com.selenium.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private Properties props;
    private String caminhoDoProperties = "src/main/resources/localhost/config.properties";

    protected PropertiesLoader() {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(caminhoDoProperties);
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getValor(String chave) {
        return (String) props.getProperty(chave);
    }
}
