package repositories;

import models.Locacao;
import repositories.DAO;

import javax.persistence.EntityManager;

public class LocacaoRepository extends DAO<Locacao> {
    public LocacaoRepository(EntityManager manager){
        super("Locacao", manager);
    }

}
