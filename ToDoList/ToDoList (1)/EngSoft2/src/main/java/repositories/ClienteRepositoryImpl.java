package repositories;

import models.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {
    private EntityManager manager;

    public ClienteRepositoryImpl(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public void salva(Cliente cliente) {
        manager.merge(cliente);
    }

    @Override
    public List<Cliente> findByName(String nome) {
        return manager.
                createQuery("select c from cliente c where l.nome = :nome", Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    @Override
    public List<Cliente> findAll() {
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}
