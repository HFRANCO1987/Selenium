package br.com.selenium.modulo.estoque;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.TesteUtil;
import org.junit.Before;
import org.junit.Test;

public class AcertoEstoqueTest extends TesteUtil{

    @Before
    public void setUp() {
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/farmacia/acertoEstoque.faces");
    }

    @Test
    public void acertoEstoque() {
        try {
            System.out.println("Cadastro Acerto Estoque");
            driverUtil.click("formAcertoEstoque:buttonInserir");
            driverUtil.selectAutoComplete("formAcertoEstoque:tab:autoCompleteProduto", "DEXA", 0);
            Boolean executeJS = (Boolean) driverUtil.executeJS("return (document.getElementById('formAcertoEstoque:dialogProdutoSaldoEstoque').style.visibility) === 'visible'");
            if (executeJS) {
                driverUtil.write("formAcertoEstoque:dataTableProdutoDispensacaoSaldoEstoque:0:inputTextQuantidade", "100");
                driverUtil.executeJS("PF('dialogSaldoEstoque').hide();");
            } else {
                driverUtil.write("formAcertoEstoque:dataTableProdutoDispensacaoSaldoEstoque:0:inputTextQuantidade", "100");
                driverUtil.click("formAcertoEstoque:tab:commandButtonAdcionaProduto");
            }
            driverUtil.write("formAcertoEstoque:tab:inputTextareaObservacao", "ABC123");
            int a = driverUtil.botaoSalvar("formAcertoEstoque:buttonSalvar", "message.criadocomsucesso");
            driverUtil.assertMessagens(a);
            driverUtil.click("formAcertoEstoque:commandButtonProcessar");
            int b = driverUtil.botaoSalvar("formAcertoEstoque:commandButtonProcessarSim", "message.entradaproduto.infomovimentacao.realizadacomsucesso");
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
        }
    }
}