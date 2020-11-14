package repository;

import builder.ImovelBuilder;
import models.Imovel;
import org.junit.jupiter.api.*;

import repositories.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class ImovelRepositoryTest {
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private ImovelRepository repository;

    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }

    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new ImovelRepository(manager);
        List<Imovel> imoveis = Arrays.asList(new Imovel[]{ImovelBuilder.umImovel().constroi(),ImovelBuilder.umImovel().comLocacao().constroi()
                , ImovelBuilder.umImovel().comEndereco("Rua dos Manacas","7","São Francisco","65076-210").constroi()
                , ImovelBuilder.umImovel().comAluguelSugerido(1000.0).constroi()});
        imoveis.forEach(imovel->{
            repository.save(imovel);
        });
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
    public void testaSalvar(){
        repository.save( ImovelBuilder.umImovel().constroi());
    }

    @Test
    public void testaBuscarTodos(){
        List<Imovel> imoveis = repository.findAll();
        imoveis.forEach(imovel -> {
            System.out.println(imovel);
        });
        Assertions.assertEquals(4,imoveis.size());

    }
    @Test
    public void testaBuscarAtivo(){
        List<Imovel> imoveis = repository.findByAtivo(true);
        Assertions.assertFalse(imoveis.isEmpty());
    }
    /*@Test
    public void testaBuscarPorEnderecoEInativos(){
        List<Imovel> imoveis = repository.findByAtivoPorEndereco(false,"Centro");
        imoveis.forEach(imovel -> {
                System.out.println(imovel);
        });
    }*/

}
