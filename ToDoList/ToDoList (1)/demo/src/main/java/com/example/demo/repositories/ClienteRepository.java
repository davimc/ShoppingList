package com.example.demo.repositories;

import com.example.demo.models.Cliente;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public class ClienteRepository  {
    private EntityManager manager;

    public ClienteRepository(EntityManager manager){
        this.manager = manager;
    }
    public void save(Cliente cliente){
        manager.merge(cliente);
    }

}
