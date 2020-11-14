package service;

import builder.ClienteBuilder;
import builder.ImovelBuilder;
import models.Aluguel;
import models.Locacao;
import models.TipoEnum;
import org.junit.jupiter.api.*;
import services.MainService;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

//LEMBRAR DE MUDAR O PERSISTENCE, PASSWORD ESTÁ SEM NADA
public class MainServiceTest {
    private MainService imobiliariaService;
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
        imobiliariaService = new MainService(manager);

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
    public void deveRecuperarAlugueisDeUmCliente(){
        List<Locacao> locacao = new ArrayList<>();
        locacao.add(imobiliariaService.realizaLocacao(
                ClienteBuilder.umCliente().constroi(), ImovelBuilder.umImovel().constroi(), 800,0.4,""));
        locacao.add(imobiliariaService.realizaLocacao(
                ClienteBuilder.umCliente().constroi(), ImovelBuilder.umImovel().comEndereco("Savassi","4","Cohama","65077210").comTipo(TipoEnum.CASA).constroi(), 800,0.4,""));
        locacao.add(imobiliariaService.realizaLocacao(
                ClienteBuilder.umCliente().constroi(), ImovelBuilder.umImovel().comEndereco("Manacas","7","São Francisco","65076210").comTipo(TipoEnum.PREDIO).constroi(), 800,0.4,""));

        List<Aluguel> alugeis = imobiliariaService.listaAlugueisPagosCliente("60727289365");

    }

}
