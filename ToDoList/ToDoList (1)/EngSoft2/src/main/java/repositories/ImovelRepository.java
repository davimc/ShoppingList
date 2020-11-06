package repositories;

import models.Imovel;

import java.util.List;

public interface ImovelRepository {
    public void salva(Imovel imovel);
    public List<Imovel> findByAtivo(boolean ativo);
    public List<Imovel> findAll();
}
