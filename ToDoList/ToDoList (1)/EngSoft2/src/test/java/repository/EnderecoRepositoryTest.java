package repository;

import models.Endereco;
import org.junit.jupiter.api.*;
import repositories.ClienteRepository;
import repositories.EnderecoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class EnderecoRepositoryTest {
    private EnderecoRepository repository;
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private String rua = "Rua dos Prazeres", numero ="537", bairro = "Centro", cep="65076210";
    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }
    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new EnderecoRepository(manager);
        repository.save(new Endereco(rua, numero, bairro, cep));
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
    public void testaEncontrarEndereco(){

        Endereco enderecoTestado = repository.findEndereco(rua,numero,bairro,cep).get();
        Assertions.assertEquals(rua,enderecoTestado.getRua());
        Assertions.assertEquals(numero,enderecoTestado.getNumero());
        Assertions.assertEquals(bairro,enderecoTestado.getBairro());
        Assertions.assertEquals(cep,enderecoTestado.getCep());
    }
    @Test
    public void testaEncontrarEnderecoDeveLancarNullPointerException(){
        Assertions.assertThrows(NoResultException.class,()->{
            Endereco enderecoTestado = repository.findEndereco(rua,"1",bairro,cep).get();
        });

    }
}
