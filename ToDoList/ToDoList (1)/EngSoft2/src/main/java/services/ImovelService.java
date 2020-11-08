package services;

import models.Imovel;
import repositories.ImovelRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ImovelService {
    ImovelRepository imovelRepository;

    public ImovelService(EntityManager manager){
        imovelRepository = new ImovelRepository(manager);
    }

    public List<Imovel> buscaImoveisInativosPorBairro(String bairro){
        List<Imovel> imoveisInativos = imovelRepository.findByAtivo(false);
        List<Imovel> imovelInativoPorBairro = new ArrayList<>();
        imoveisInativos.forEach(imovel -> {
            if(imovel.getEndereco().getBairro().equals(bairro)) imovelInativoPorBairro.add(imovel);
        });
        return imovelInativoPorBairro;
    }
}
