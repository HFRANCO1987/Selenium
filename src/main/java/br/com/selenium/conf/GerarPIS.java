package br.com.selenium.conf;

import java.util.Random;

public class GerarPIS {

    public static String GeraPisFinal() {
        Random r = new Random();
        int num = 0;
        String numero = "";
        for (int i = 0; i < 10; i++) {
            num = r.nextInt(9);
            numero += Integer.toString(num);
        }
        int a = Integer.parseInt(String.valueOf(numero.charAt(0)));
        int b = Integer.parseInt(String.valueOf(numero.charAt(1)));
        int c = Integer.parseInt(String.valueOf(numero.charAt(2)));
        int d = Integer.parseInt(String.valueOf(numero.charAt(3)));
        int e = Integer.parseInt(String.valueOf(numero.charAt(4)));
        int f = Integer.parseInt(String.valueOf(numero.charAt(5)));
        int g = Integer.parseInt(String.valueOf(numero.charAt(6)));
        int h = Integer.parseInt(String.valueOf(numero.charAt(7)));
        int i = Integer.parseInt(String.valueOf(numero.charAt(8)));
        int j = Integer.parseInt(String.valueOf(numero.charAt(9)));
        int soma = (3 * a) + (2 * b) + (9 * c) + (8 * d) + (7 * e) + (6 * f) + (5 * g) + (4 * h) + (3 * i) + (2 * j);
        int resto = soma % 11;
        int digitoVerificador = 11 - resto;
        if (digitoVerificador == 10 || digitoVerificador == 11) {
            digitoVerificador = 0;
        }
        numero += String.valueOf(digitoVerificador);
        return numero;
    }
}
