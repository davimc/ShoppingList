package services;

import models.Cliente;
import repositories.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

public class ClienteService {
    private ClienteRepository repository;

    public ClienteService(EntityManager manager) {
        repository = new ClienteRepository(manager);
    }

    public Cliente salva(Cliente cliente){
        try {
            Cliente clienteExistente = repository.findByCpf(cliente.getCpf());
            if (clienteExistente != null)
                return clienteExistente;
        }catch(NoResultException e){
            repository.save(cliente);
            return repository.findByCpf(cliente.getCpf());
        }
        return null;
    }

    public Cliente encontraPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
