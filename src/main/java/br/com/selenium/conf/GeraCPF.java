package br.com.selenium.conf;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author linuxtest
 */
public class GeraCPF {

    private ArrayList<Integer> listaAleatoria = new ArrayList();
    private ArrayList<Integer> listaNumMultiplicados = null;

    public int geraNumAleatorio() {
        int numero = (int) (Math.random() * 10);
        return numero;
    }

    public ArrayList geraCPFParcial() {
        for (int i = 0; i < 9; i++) {
            listaAleatoria.add(geraNumAleatorio());
        }
        return listaAleatoria;
    }

    public ArrayList geraPrimeiroDigito() {
        listaNumMultiplicados = new ArrayList();
        int primeiroDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 10;
        for (Iterator it = listaAleatoria.iterator(); it.hasNext();) {
            Integer item = (Integer) it.next();
            listaNumMultiplicados.add(item * peso);
            peso--;
        }
        for (Iterator it = listaNumMultiplicados.iterator(); it.hasNext();) {
            Integer item = (Integer) it.next();
            totalSomatoria += item;
        }
        restoDivisao = (totalSomatoria % 11);
        if (restoDivisao < 2) {
            primeiroDigito = 0;
        } else {
            primeiroDigito = 11 - restoDivisao;
        }
        listaAleatoria.add(primeiroDigito);
        return listaAleatoria;
    }

    public ArrayList geraSegundoDigito() {
        listaNumMultiplicados = new ArrayList();
        int segundoDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 11;

        for (Integer integer : listaAleatoria) {
            listaNumMultiplicados.add(integer * peso);
            peso--;
        }
        for (Integer integer : listaAleatoria) {
            totalSomatoria += integer;
        }

        restoDivisao = (totalSomatoria % 11);
        if (restoDivisao < 2) {
            segundoDigito = 0;
        } else {
            segundoDigito = 11 - restoDivisao;
        }
        listaAleatoria.add(segundoDigito);
        return listaAleatoria;
    }

    public String geraCPFFinal() {

        String cpfValido = geraCpf();

        while (true) {
            if (!ValidarCPF(cpfValido)) {
                System.out.println("CPF invalido: "+cpfValido);
                cpfValido = geraCpf();
            } else {
                break;
            }
        }
        return cpfValido;
    }

    public String geraCpf() {
        geraCPFParcial();
        geraPrimeiroDigito();
        geraSegundoDigito();
        String cpf = "";
        for (Object item : listaAleatoria) {
            cpf += item;
        }
        try {
            MaskFormatter mf = new MaskFormatter("###########");
            mf.setValueContainsLiteralCharacters(false);
            cpf = mf.valueToString(cpf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cpf;
    }

    private static boolean ValidarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || CPFPadrao(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean CPFPadrao(String cpf) {
        if (cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {

            return true;
        }

        return false;
    }
}
