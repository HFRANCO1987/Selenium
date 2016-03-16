package br.com.selenium.modulo.estoque;

import br.com.selenium.conf.Config;
import br.com.selenium.conf.TesteUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovimentacaoProdutoEntradaTest extends TesteUtil {

    private String dataAtualString;

    @Before
    public void setUp(){
        driverUtil.navigateTo(Config.TOP_URL + "/salute/pages/estoque/movimentacaoProduto/movimentacaoProduto.faces?tipo=ENTRADA");
    }

    @Test
    public void movimentacaoProduto() {
        try {
            System.out.println("Cadastro Movimentação Produto - Entrada");
            
            driverUtil.click("formEdicaoMovimentacaoProduto:buttonInserir");
            driverUtil.selectComboBox("formEdicaoMovimentacaoProduto:tab:selectOneMenuTipoDocumento", "NOTA FISCAL");
            driverUtil.write("formEdicaoMovimentacaoProduto:tab:inputTextNumeroNota", "12345");
            driverUtil.selectAutoComplete("formEdicaoMovimentacaoProduto:tab:autoCompleteFornecedorOrigem", "FURP", 0);
            driverUtil.write("formEdicaoMovimentacaoProduto:tab:autoCompleteNumeroLote", "123abc");
            driverUtil.write("formEdicaoMovimentacaoProduto:tab:calendarDataDeValidade_input", (dataAtualString));
            driverUtil.selectAutoComplete("formEdicaoMovimentacaoProduto:tab:autoCompleteProduto", "DEXA", 0);
            driverUtil.selectAutoComplete("formEdicaoMovimentacaoProduto:tab:autoCompleteFabricante", "FURP", 0);
            driverUtil.write("formEdicaoMovimentacaoProduto:tab:inputMoney:_input", "0,00010");
            driverUtil.write("formEdicaoMovimentacaoProduto:tab:prod_vl_qtd", "10");
            driverUtil.click("formEdicaoMovimentacaoProduto:tab:commandLinkAdcionarProduto");
            int a = driverUtil.botaoSalvar("formEdicaoMovimentacaoProduto:buttonSalvar", "message.criadocomsucesso");
            driverUtil.assertMessagens(a);
            driverUtil.click("formEdicaoMovimentacaoProduto:commandButtonProcessar");
            int b = driverUtil.botaoSalvar("formEdicaoMovimentacaoProduto:commandButtonProcessarSim", "message.entradaproduto.infomovimentacao.realizadacomsucesso", TEMPO);
            driverUtil.assertMessagens(b);
        } catch (Exception e) {
            System.out.println(" - Erro.");
            Assert.assertTrue(false);
        }
    }

    public MovimentacaoProdutoEntradaTest() {
        Calendar data = Calendar.getInstance();
        data.add(Calendar.DAY_OF_YEAR, +10);
        dataAtualString = new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
    }
}