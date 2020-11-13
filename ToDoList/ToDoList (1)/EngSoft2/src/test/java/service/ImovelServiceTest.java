package service;

import builder.ImovelBuilder;
import models.Imovel;
import models.TipoEnum;
import org.junit.jupiter.api.*;
import repositories.ImovelRepository;
import services.ImovelService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//LEMBRAR DE MUDAR O PERSISTENCE, PASSWORD ESTÁ SEM NADA
public class ImovelServiceTest {
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private ImovelRepository repository;
    private ImovelService service;
    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }

    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new ImovelRepository(manager);
        service = new ImovelService(manager);
        List<Imovel> imoveis = Arrays.asList(new Imovel[]{
                ImovelBuilder.umImovel().constroi()
                , ImovelBuilder.umImovel().comAlocacao().constroi()
                , ImovelBuilder.umImovel().comAluguelSugerido(500).constroi()
                , ImovelBuilder.umImovel().comEndereco("Rua dos Manacas",7,"São Francisco","65076-210").comAluguelSugerido(900).constroi()
                , ImovelBuilder.umImovel().comAluguelSugerido(1000.0).constroi(),ImovelBuilder.umImovel().comTipo(TipoEnum.CASA).constroi()});
        imoveis.forEach(imovel->{
            this.repository.save(imovel);
        });;
    }
    @AfterEach

    public void depois(){
        manager.getTransaction().rollback();
    }
    @AfterAll
    public static  void fim(){
        emf.close();
    }

    @Test
    public void testaAlocacao(){
        Imovel imovel = repository.findById(1L);
        service.alocaImovel(imovel);
        Assertions.assertTrue(imovel.isAtivo());
    }
    @Test
    public void testaApartamentosDisponiveis(){
        List<Imovel> imoveis = repository.findByAtivoPorTipo(false, TipoEnum.APARTAMENTO);
        imoveis.forEach(imovel -> {
            System.out.println(imovel);
            Assertions.assertEquals(TipoEnum.APARTAMENTO, imovel.getTipo());
            Assertions.assertEquals(false, imovel.isAtivo());
        });
    }
    @Test
    public void testaLimiteDePrecoPorBairro(){
        List<Imovel> imoveis = service.listaImoveisDisponiveisComPrecoMaximo(700);
        imoveis.forEach(imovel -> {
            System.out.println(imovel);
            Assertions.assertTrue((imovel.getAluguelSugerido()<=700));
        });
    }

}
