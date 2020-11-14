package repositories;

import models.Endereco;
import models.Imovel;
import models.TipoEnum;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ImovelRepository extends DAO<Imovel> {
    private EntityManager manager;

    public ImovelRepository(EntityManager manager) {
        super("Imovel", manager);
    }

    public Imovel findById(Long id){
        return (Imovel)getManager().createQuery("from Imovel where id=:id").setParameter("id",id).getSingleResult();
    }
    public List<Imovel> findByAtivo(boolean ativo) {
        return getManager().createQuery("from Imovel where isAtivo=:ativo").setParameter("ativo",ativo).getResultList();
    }
    public List<Imovel> findByAtivoPorTipo(boolean ativo, TipoEnum tipo){
        return getManager().createQuery("from Imovel where isAtivo=:ativo and tipo>=:tipo").setParameter("ativo",ativo).setParameter("tipo",tipo)
                .getResultList();
    }
    public Optional<Imovel> findByEndereco(Long idEndereco){
        return Optional.of((Imovel) getManager().createQuery("from Imovel where enderecoId=:idEndereco").setParameter("idEndereco",idEndereco).getSingleResult());
    }
    /*public List<Imovel> findByAtivoPorEndereco(boolean ativo,String endereco){
        return getManager().createQuery("from Imovel where isAtivo=:ativo and endereco=:endereco")
                .setParameter("endereco",endereco)
                .setParameter("ativo",ativo)
                .getResultList();
    }*/
   /* public List<Imovel> findByAluguelSugeridoMaximo(double valor){
        return getManager().createQuery("from Imovel where aluguel_sugerido<=:valor").setParameter("valor",valor).getResultList()/
    }*/
}
