package br.com.selenium.conf;

import javax.swing.text.MaskFormatter;

public class GeraCNPJ {
    
    public static String geraCNPJParcial() {
        try {
            return formatarString(geraCNPJ(), "##.###.###/####-##");
        } catch (Exception ex) {
            return null;
        }
    }
    

    private static String geraCNPJ() {
        Long n = 9l;
        Long n1 = randomiza(n);
        Long n2 = randomiza(n);
        Long n3 = randomiza(n);
        Long n4 = randomiza(n);
        Long n5 = randomiza(n);
        Long n6 = randomiza(n);
        Long n7 = randomiza(n);
        Long n8 = randomiza(n);
        Long n9 = 0l;
        Long n10 = 0l;
        Long n11 = 0l;
        Long n12 = 1l;

        Long d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

        d1 = 11 - (mod(d1, 11l));
        if (d1 >= 10) {
            d1 = 0l;
        }
        Long d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;
        d2 = 11 - (mod(d2, 11l));
        if (d2 >= 10) {
            d2 = 0l;
        }
        return "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;
    }

    private static Long randomiza(Long n) {
        Long ranNum = Math.round(Math.random() * n);
        return ranNum;
    }

    private static Long mod(Long dividendo, Long divisor) {
        return Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    private static String formatarString(String texto, String mascara)throws Exception{
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(texto);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
