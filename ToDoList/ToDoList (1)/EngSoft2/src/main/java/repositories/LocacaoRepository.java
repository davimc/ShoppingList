package repositories;

import models.Cliente;
import models.Imovel;
import models.Locacao;
import repositories.DAO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class LocacaoRepository extends DAO<Locacao> {
    public LocacaoRepository(EntityManager manager){
        super("Locacao", manager);
    }

    public Locacao findLocacao(Cliente cliente, Imovel imovel){
        return
                (Locacao ) getManager()
                        .createQuery("from Locacao where cliente_id=:cliente and imovel_id =:imovel")
                        .setParameter("cliente",cliente.getId())
                        .setParameter("imovel", imovel.getId())
                        .getSingleResult();
    }

    public List<Locacao> listByCliente(Cliente cliente){
        return getManager().createQuery("from Locacao where cliente_id=:clienteId").setParameter("clienteId", cliente.getId()).getResultList();
    }
    public List<Locacao> listByImovel(Imovel imovel){
        return getManager().createQuery("from Locacao where imovel_id=:imovelId").setParameter("imovelId", imovel.getId()).getResultList();
    }

}
