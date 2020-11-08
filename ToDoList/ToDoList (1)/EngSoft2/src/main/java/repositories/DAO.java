package repositories;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class DAO<T>{
    private EntityManager manager;
    private String tipo;

    public DAO(String tipo, EntityManager manager){
        this.manager = manager;
        this.tipo = tipo;
    }
    public void save(T t){
        manager.merge(t);
    }

    public List<T> findAll(){
        return manager.createQuery("from "+tipo)
                .getResultList();
    }

    public EntityManager getManager() {
        return manager;
    }
}
