package br.com.selenium.modulo.estoque;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.GeraCNPJ;
import br.com.selenium.conf.TesteUtil;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


import org.junit.Assert;

public class FabricanteTest extends TesteUtil {

    private static final String RAZAO_SOCIAL = "REDVITA (Razção Social)" + " " + new Random().nextLong();
    private static String NOME_FANTASIA = "REDVITA (Nome Fantasia)" + " " + new Random().nextLong();
    private static String CNPJ = GeraCNPJ.geraCNPJParcial();
    private static String LOGRADOURO = "rua";
    private static String NUMERO = "1234";
    private static String COMPLEMENTO = "apartamento";
    private static String CONDOMINIO = "A";
    private static String BLOCO = "A";
    private static String PISO = "3";
    private static String APTOSALA = "31";
    
    @Before
    public void navigateTo() {
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/estoque/fabricante.faces");
    }

    @Test
    public void fabricante() {
        try {
            System.out.println("Inserir Fabricante");
            driverUtil.click("formEdicaoFabricante:buttonInserir");
            driverUtil.write("formEdicaoFabricante:tab:inputTextRazaoSocial", RAZAO_SOCIAL);
            driverUtil.write("formEdicaoFabricante:tab:nomeFantasia", NOME_FANTASIA);
            driverUtil.write("formEdicaoFabricante:tab:inputTextCnpj", CNPJ);
            driverUtil.selectAutoComplete("formEdicaoFabricante:tab:swEndereco:autoCompleteLogradouro", LOGRADOURO, 0);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextNumeroSemCaracterizacao", NUMERO);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextComplementoSemExibeCaracterizacao", COMPLEMENTO);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextCondominioSemExibeCaracterizacao", CONDOMINIO);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextBlocoSemExibeCaracterizacao", BLOCO);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextPisoSemExibeCaracterizacao", PISO);
            driverUtil.write("formEdicaoFabricante:tab:swEndereco:inputTextAptoSalaSemExibeCaracterizacao", APTOSALA);
            int b = driverUtil.botaoSalvar("formEdicaoFabricante:buttonSalvar", "message.criadocomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void alterar() {
        try {
            System.out.println("Alterar Fabricante");
            driverUtil.selectAutoComplete("formEdicaoFabricante:tab:autoCompleteRazaoSocial", "FURP", 0);
            driverUtil.click("formEdicaoFabricante:buttonEditar");
            int b = driverUtil.botaoSalvar("formEdicaoFabricante:buttonSalvar", "message.alteradocomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }
}
