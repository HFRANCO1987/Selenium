/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.selenium;

import br.com.selenium.conf.ChromeDriverUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author alci
 */
@RunWith(Suite.class)
@SuiteClasses({
////        FormaApresentacaoEmbalagemTest.class,
////        UnidadeDeMedidaTest.class,
//    GrupoDeProdutoTest.class,
//    ProdutoTest.class,
////            FabricanteTest.class,
////            EstoqueTest.class,
////        EntradaDeProdutosSemNotaTest.class,
////        ViaDeAdiministracaoTest.class,
////        PrincipioAtivoTest.class,
////        UsoMedicamentoTest.class,
////        IntervaloMedicacaoTest.class,
////        PrescricaoTest.class,
////        ProdutoFarciaTest.class,
//    DispensacaoProdutoTest.class
})
public class HelloWorld {
    
    private static ChromeDriverUtil driverUtil = new ChromeDriverUtil();
    
    public HelloWorld() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        driverUtil.login();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
//     @Test
//     public void hello() {
//         System.out.println("TESTE");
//     }
}
