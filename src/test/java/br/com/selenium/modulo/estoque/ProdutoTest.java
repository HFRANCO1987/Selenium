package br.com.selenium.modulo.estoque;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.TesteUtil;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class ProdutoTest extends TesteUtil {

    @Before
    public void navigateTo() {
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/estoque/produto.faces");
    }

    @Test
    public void inserir() {
        try {
            System.out.println("Cadastro Produto");
            driverUtil.click("formEdicaoProduto:buttonInserir");
            driverUtil.write("formEdicaoProduto:tab:inputTextProdutoDescricao", "poliminerais e poli vitaminicos");
            driverUtil.selectComboBox("formEdicaoProduto:tab:grupoNivel1", "MEDICAMENTO");
            driverUtil.selectAutoComplete("formEdicaoProduto:tab:autoCompleteFormaDeApresentacao", "frasc", 0);
            driverUtil.write("formEdicaoProduto:tab:inputTextQuantidade", "100");
            driverUtil.selectAutoComplete("formEdicaoProduto:tab:autoCompleteUnidadeDeMedida", "mili", 0);
            driverUtil.click("formEdicaoProduto:tab:commandButtonAdicionar");
            int b = driverUtil.botaoSalvar("formEdicaoProduto:buttonSalvar", "message.criadocomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void alterar() {
        try {
            System.out.println("Alterar Produto");
            driverUtil.executeJS("document.getElementsByClassName('ui-state-default ui-corner-top')[1].click();");
            driverUtil.selectTable("formEdicaoProduto:tab:dataTableProduto", 2, 1);
            driverUtil.click("formEdicaoProduto:buttonEditar");
            int b = driverUtil.botaoSalvar("formEdicaoProduto:buttonSalvar", "message.alteradocomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }
}
