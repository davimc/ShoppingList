package com.example.demo.Testes;

import com.example.demo.models.Cliente;
import com.example.demo.repositories.ClienteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class ClienteRepositoryTeste {
    private EntityManager manager;
    private static EntityManagerFactory emf;
    private ClienteRepository repository;

    @BeforeAll
    public static void inicio() {
        emf = Persistence.createEntityManagerFactory("jdbc:h2:mem:testdb");
    }

    @BeforeEach
    public void antes() {
        manager = emf.createEntityManager();
        manager.getTransaction().begin();
        repository = new ClienteRepository(manager);
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
    public void testaSalvarCliente(){
        repository.save(new Cliente("DAvi","60727289365","982186943","","davimatosc@hotmail.com", LocalDate.now()));
    }
}
