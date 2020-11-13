package service;

import builder.ClienteBuilder;
import builder.ImovelBuilder;
import builder.LocacaoBuilder;
import models.Imovel;
import models.TipoEnum;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import repositories.AluguelRepository;
import repositories.ImovelRepository;
import services.AluguelService;
import services.ImovelService;
import services.LocacaoService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

//LEMBRAR DE MUDAR O PERSISTENCE, PASSWORD ESTÁ SEM NADA
public class AluguelServiceTest {
    private static EntityManagerFactory emf;
    private EntityManager manager;
    private AluguelRepository repository;
    private AluguelService service;
    private LocacaoService locacaoService;

    @BeforeAll
    public static void inicio(){
        emf = Persistence.createEntityManagerFactory("imobiliariaPU_test");
    }

    @BeforeEach
    public void antes(){
        manager = emf.createEntityManager();
        manager.getTransaction().begin();

        repository = new AluguelRepository(manager);
        service = new AluguelService(manager);
        locacaoService = new LocacaoService(manager);

        locacaoService.locarImovel(LocacaoBuilder.umaLocacao().constroi());
    }
    @AfterEach

    public void depois(){
        manager.getTransaction().rollback();
    }
    @AfterAll
    public static  void fim(){
        emf.close();
    }

}
