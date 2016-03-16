package br.com.selenium.conf;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alci
 */
public class GerarCns {

    public static Integer getRandomInt(int min, int max) {
        Double a = Math.random();
        return new Double(Math.floor(a * (max - min)) + min).intValue();
    }

    private static String geradorNumero() {
        Integer[] a = {getRandomInt(1, 3), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10), getRandomInt(0, 10)};

        double soma = (a[0] * 3) + (a[1] * 2) + (a[2] * 9) + (a[3] * 8) + (a[4] * 7) + (a[5] * 6) + (a[6] * 5) + (a[7] * 4) + (a[8] * 3) + (a[9] * 2);
        double resto = soma % 11;
        double dv = 11 - resto;

        if (dv == 10 || dv == 11) {
            dv = 0;
        }

        String b = StringUtils.join(a, "") + new Double(dv).intValue();
        return b;
    }

    public static String geradorCNS() {
        String resultado = null;
        Integer resto;
        Integer dv;

        String pis = geradorNumero();

        Integer soma = (((new Integer(pis.substring(0, 1))) * 15)
                + ((new Integer(pis.substring(1, 2))) * 14)
                + ((new Integer(pis.substring(2, 3))) * 13)
                + ((new Integer(pis.substring(3, 4))) * 12)
                + ((new Integer(pis.substring(4, 5))) * 11)
                + ((new Integer(pis.substring(5, 6))) * 10)
                + ((new Integer(pis.substring(6, 7))) * 9)
                + ((new Integer(pis.substring(7, 8))) * 8)
                + ((new Integer(pis.substring(8, 9))) * 7)
                + ((new Integer(pis.substring(9, 10))) * 6)
                + ((new Integer(pis.substring(10, 11))) * 5));

        resto = soma % 11;

        dv = 11 - resto;

        if (dv == 11) {
            dv = 0;
        }
        if (dv == 10) {
            soma = (((new Integer(pis.substring(0, 1))) * 15)
                    + ((new Integer(pis.substring(1, 2))) * 14)
                    + ((new Integer(pis.substring(2, 3))) * 13)
                    + ((new Integer(pis.substring(3, 4))) * 12)
                    + ((new Integer(pis.substring(4, 5))) * 11)
                    + ((new Integer(pis.substring(5, 6))) * 10)
                    + ((new Integer(pis.substring(6, 7))) * 9)
                    + ((new Integer(pis.substring(7, 8))) * 8)
                    + ((new Integer(pis.substring(8, 9))) * 7)
                    + ((new Integer(pis.substring(9, 10))) * 6)
                    + ((new Integer(pis.substring(10, 11))) * 5) + 2);
            resto = soma % 11;
            dv = 11 - resto;
            resultado = pis + "001" + dv;
        } else {
            resultado = pis + "000" + dv;
        }

        return resultado;
    }
}
