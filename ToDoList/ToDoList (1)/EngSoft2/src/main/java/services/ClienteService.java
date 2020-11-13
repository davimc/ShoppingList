package services;

import models.Cliente;
import repositories.ClienteRepository;

import javax.persistence.EntityManager;


public class ClienteService {
    ClienteRepository repository;

    public ClienteService(EntityManager manager){
        repository = new ClienteRepository(manager);
    }

    public void salva(Cliente cliente){
        Cliente clienteExistente = repository.findByCpf(cliente.getCpf());
        if(clienteExistente !=null && clienteExistente.equals(cliente))
            cliente = clienteExistente;
        repository.save(cliente);
    }
    public void atualiza(Cliente cliente){
        repository.save(cliente);
    }

}
