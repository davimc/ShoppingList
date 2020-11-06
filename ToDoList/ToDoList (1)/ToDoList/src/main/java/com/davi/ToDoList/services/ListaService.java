package com.davi.ToDoList.services;

import com.davi.ToDoList.domains.Lista;
import com.davi.ToDoList.exceptions.ListaException;
import com.davi.ToDoList.repositories.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service()
public class ListaService {
    @Autowired
    private ListaRepository listaRepository;

    public void insert(@org.jetbrains.annotations.NotNull Lista lista){
        if(!listaRepository.findByName(lista.getName()).isPresent()){
            try{
                listaRepository.save(lista);
            }catch(ConstraintViolationException e){
                e.getConstraintViolations();
            }
        }
    }
    public void update(String listaName) throws ListaException {
        Lista lista = listaRepository.findByName(listaName).get();
        if(lista!=null)
            listaRepository.save(lista);
        else
            throw new ListaException(new Exception("Lista não foi encontrada"));
    }
    public List<Lista> listByName(String name){
        return listaRepository.listListas(name);
    }
}
