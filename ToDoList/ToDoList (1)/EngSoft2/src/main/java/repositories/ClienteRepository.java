package repositories;

import models.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository extends DAO<Cliente> {

    public ClienteRepository(EntityManager manager) {
        super("Cliente",manager);
    }



    public List<Cliente> findByName(String nome) {
        System.out.println("algo");
        return getManager().
                createQuery("from Cliente where nome=:nome").setParameter("nome", nome)
                .getResultList();
    }


}
