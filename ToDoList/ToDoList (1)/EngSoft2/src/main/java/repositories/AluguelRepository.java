package repositories;

import models.Aluguel;
import models.Locacao;

import javax.persistence.EntityManager;
import java.util.List;

public class AluguelRepository extends DAO<Aluguel> {
    public AluguelRepository(EntityManager manager){
        super("Aluguel",manager);
    }

    public List<Aluguel> findByLocacao(Locacao locacao){
        return getManager().createQuery("from Aluguel where locacao_id=:locacaoId").setParameter("locacaoId",locacao.getId()).getResultList();
    }
}
