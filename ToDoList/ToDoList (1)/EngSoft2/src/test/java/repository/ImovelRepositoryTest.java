package repository;

import models.Cliente;
import models.Imovel;
import org.junit.jupiter.api.*;

import repositories.ImovelRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ImovelRepositoryTest {
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private ImovelRepositoryImpl repository;

    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }

    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new ImovelRepositoryImpl(manager);
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
    public void testaBuscarTodos(){
        List<Imovel> imoveis = repository.findByAtivo(true);
        System.out.println(imoveis.size());
    }
}
