package repositories;

import models.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelRepositoryImpl implements ImovelRepository {
    private EntityManager manager;

    public ImovelRepositoryImpl(EntityManager manager) {
        this.manager = manager;

    }


    @Override
    public void salva(Imovel imovel) {

    }

    @Override
    public List<Imovel> findByAtivo(boolean ativo) {
        return manager.createQuery("select i from imovel i where i.isAtivo=:ativo", Imovel.class)
                .setParameter("ativo", ativo).getResultList();
    }

    @Override
    public List<Imovel> findAll() {
        return null;
    }
}
