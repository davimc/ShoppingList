package repository;

import builder.ClienteBuilder;
import models.Cliente;
import org.junit.jupiter.api.*;
import repositories.ClienteRepository;
import repositories.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteRepositoryTest {
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private ClienteRepository repository;

    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }
    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new ClienteRepository(manager);
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
        repository.save(new ClienteBuilder().umCliente().constroi());
    }
    @Test
    public void testaBuscarTodos(){
        List<Cliente> clientes = repository.findAll();
        System.out.println(clientes.size());
    }
    @Test
    public void testaAcharPorNome(){
        repository.save(ClienteBuilder.umCliente().constroi());
        List<Cliente> clientes = repository.findByName("Davi Matos de Carvalho");
        Assertions.assertEquals("Davi Matos de Carvalho", clientes.get(0).getNome());
    }
}
