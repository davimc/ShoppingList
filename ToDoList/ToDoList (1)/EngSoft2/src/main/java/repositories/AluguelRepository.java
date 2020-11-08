package repositories;

import models.Aluguel;

import javax.persistence.EntityManager;

public class AluguelRepository extends DAO<Aluguel> {
    public AluguelRepository(EntityManager manager){
        super("Aluguel",manager);
    }
}
