package br.com.selenium.modulo.administracao;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.GeraCNPJ;
import br.com.selenium.conf.TesteUtil;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InstituicaoTest extends TesteUtil {

    public static String razaoSocial = "Razao" + " " + new Random().nextLong();
    public static String nomeFantasia = "Fantasia" + " " + new Random().nextLong();
    public static String CNPJ = GeraCNPJ.geraCNPJParcial();

    @Before
    public void setUp() {
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/base/instituicao.faces");
    }

    @Test
    public void instituicao() throws InterruptedException {
        try {
            System.out.println("Instituição");
            driverUtil.click("formEdicao:buttonInserir", TEMPO);
            driverUtil.write("formEdicao:tab:inputTextRazaoSocial", razaoSocial, TEMPO);
            driverUtil.write("formEdicao:tab:nomeFantasia", nomeFantasia, TEMPO);
            driverUtil.write("formEdicao:tab:cnpj", CNPJ, TEMPO);
            driverUtil.selectAutoComplete("formEdicao:tab:swEndereco:autoCompleteLogradouro", "RUA", 0);
            driverUtil.write("formEdicao:tab:swEndereco:inputTextNumeroSemCaracterizacao", "123", TEMPO);
            driverUtil.clickTab("formEdicao:tab", 2, TEMPO);
            driverUtil.waitInSecond(TEMPO);
            driverUtil.write("formEdicao:tab:contato", "exemplo@email.com", TEMPO);
            driverUtil.click("formEdicao:tab:commandButtonAdicionarContato", TEMPO);
            int b = driverUtil.botaoSalvar("formEdicao:buttonSalvar", "message.estabelecimentotercerizado.criadocomsucesso", TEMPO);
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.print(" - Erro.");
            Assert.assertTrue(false);
        }
    }
}
