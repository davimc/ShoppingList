package service;

import builder.ClienteBuilder;
import builder.ImovelBuilder;
import builder.LocacaoBuilder;
import models.*;
import org.junit.jupiter.api.*;
import services.MainService;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//LEMBRAR DE MUDAR O PERSISTENCE, PASSWORD ESTÁ SEM NADA
public class MainServiceTest {
    private MainService mainService;
    private static EntityManagerFactory emf;
    private EntityManager manager;


    @BeforeAll
    public static void inicio() {
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }

    @BeforeEach
    public void antes() {
        manager = emf.createEntityManager();
        manager.getTransaction().begin();
        mainService = new MainService(manager);

        Imovel[] imoveis ={
                ImovelBuilder.umImovel().comEndereco("Rua dos prazeres","500","Centro","5020-460").constroi(),
                ImovelBuilder.umImovel().comEndereco("Rua dos manacas","7","Sao francisco","65076-210").constroi()
        };
        Cliente[] clientes = {
                ClienteBuilder.umCliente().comCpf("65055500022").comNome("Eren Camado").constroi()
        };
        Locacao[] locacaoes = {
            LocacaoBuilder.umaLocacao().constroi(),
            LocacaoBuilder.umaLocacao().comCliente(clientes[0]).comImovel(imoveis[0]).constroi(),
            LocacaoBuilder.umaLocacao().comImovel(imoveis[1]).constroi()
        };

    }

    @AfterEach

    public void depois() {
        manager.getTransaction().rollback();
    }

    @AfterAll
    public static void fim() {
        emf.close();
    }


    @Test
    public void deveRecuperarAlugueisPagosDeUmCliente(){
        List<Locacao> locacao = new ArrayList<>();
        System.out.println(ClienteBuilder.umCliente().constroi().getCpf()+"   "+ ImovelBuilder.umImovel().constroi().getAluguelSugerido());
        mainService.realizaLocacaoComPrimeiroAluguel(ClienteBuilder.umCliente().constroi(), ImovelBuilder.umImovel().constroi(), 800,0.4,"");
        /*locacao.add(mainService.realizaLocacaoComPrimeiroAluguel(
                ClienteBuilder.umCliente().constroi(), ImovelBuilder.umImovel().constroi(), 800,0.4,""));*/

        /*mainService.pagaAluguel(locacao.get(0),locacao.get(0).getValorAluguel());
        List<Aluguel> alugueisPagos = mainService.listaAlugueisPagosCliente("60727289365");*/
        /*System.out.println(alugueisPagos.size()+"Ok");
        Assertions.assertEquals(locacao.get(0).getAlugueis(),alugueisPagos);*/

    }


}
