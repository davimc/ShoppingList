package repositories;

import models.Aluguel;
import models.Locacao;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AluguelRepository extends DAO<Aluguel> {
    public AluguelRepository(EntityManager manager){
        super("Aluguel",manager);
    }


    public List<Aluguel> findByLocacao(Locacao locacao){
        return getManager().createQuery("from Aluguel where locacao_id=:locacaoId").setParameter("locacaoId",locacao.getId()).getResultList();
    }
    public Aluguel findByDataVencimento(Locacao locacao, LocalDate dataVencimento){
        return (Aluguel)
                getManager().createQuery("from Aluguel where locacao_id=:locacaoI and dataVencimento=:data")
                        .setParameter("locacaoId",locacao.getId())
                        .setParameter("data", dataVencimento)
                        .getSingleResult();
    }
}
