package br.com.selenium.modulo.estoque;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.GeraCNPJ;
import br.com.selenium.conf.TesteUtil;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FornecedorTest extends TesteUtil {

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
    public void setUp() {
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/estoque/fornecedor.faces");
    }

    @Test
    public void Inserir() {
        try {
            System.out.println("Inserir Fornecedor");
            driverUtil.click("formEdicao:buttonInserir");
            driverUtil.write("formEdicao:tab:inputTextRazaoSocial", RAZAO_SOCIAL);
            driverUtil.write("formEdicao:tab:nomeFantasia", NOME_FANTASIA);
            driverUtil.write("formEdicao:tab:inputTextCnpj", CNPJ);
            driverUtil.selectAutoComplete("formEdicao:tab:swEndereco:autoCompleteLogradouro", LOGRADOURO, 0);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextNumeroSemCaracterizacao", NUMERO);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextComplementoSemExibeCaracterizacao", COMPLEMENTO);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextCondominioSemExibeCaracterizacao", CONDOMINIO);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextBlocoSemExibeCaracterizacao", BLOCO);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextPisoSemExibeCaracterizacao", PISO);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextAptoSalaSemExibeCaracterizacao", APTOSALA);
            int b = driverUtil.botaoSalvar("formEdicao:buttonSalvar", "message.criadocomsucesso");
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
            driverUtil.selectAutoComplete("formEdicao:tab:autoCompleteRazaoSocial", "FURP", 0);
            driverUtil.click("formEdicao:buttonEditar");
            int b = driverUtil.botaoSalvar("formEdicao:buttonSalvar", "message.alteradocomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }
}
