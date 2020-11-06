package repositories;

import models.Cliente;

import java.util.List;

public interface ClienteRepository {
    public void salva(Cliente cliente);
    public List<Cliente> findByName(String nome);
    public List<Cliente> findAll();
}
