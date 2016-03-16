package br.com.selenium.conf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Source implements Serializable {

    private static final long serialVersionUID = -3507150326063828302L;

    private Integer id;

    private Map<String, Object> values = new HashMap<String, Object>();

    public static Source criarInstancia() {
        Source solicitacaoProdutoPojo = new Source();
        return criarInstancia(solicitacaoProdutoPojo);
    }

    public static Source criarInstancia(Source solicitacaoProdutoPojo) {
        if (solicitacaoProdutoPojo.values == null) {
            solicitacaoProdutoPojo.values = new HashMap<String, Object>();
        }
        return solicitacaoProdutoPojo;
    }

    public static ArrayList<Source> orderBy(ArrayList<Source> sourceList, final String orderBy) {
        Collections.sort(sourceList, new Comparator<Source>() {
            public int compare(Source o1, Source o2) {
                return ((String) o1.next(orderBy)).compareTo(((String) o2.next(orderBy)));
            }
        });
        return sourceList;
    }
    
    public String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public void put(String key, Object object) {
        values.put(key, object);
    }

    public Object next(String key) {
        Object get = values.get(key);
        return get;
    }

    public void remover(String key) {
        values.remove(key);
    }

    public void replace(String key, Object object) {
        values.remove(key);
        values.put(key, object);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}