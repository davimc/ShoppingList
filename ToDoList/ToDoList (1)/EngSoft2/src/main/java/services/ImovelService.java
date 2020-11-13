package services;

import models.Endereco;
import models.Imovel;
import models.TipoEnum;
import repositories.ImovelRepository;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ImovelService {
    ImovelRepository imovelRepository;

    public ImovelService(EntityManager manager){
        imovelRepository = new ImovelRepository(manager);
    }
    public void criaImovel(TipoEnum tipo, Endereco endereco, double metragem,double aluguelSugerido,int nBanheiro,int nSuite, int nGaragem, int nDormitorio, String obs){
        imovelRepository.save(new Imovel(tipo,endereco,false,metragem,nDormitorio,nBanheiro,nSuite,nGaragem,aluguelSugerido,obs));
    }
    public void alocaImovel(Imovel imovel){
        imovel.setAtivo(true);
        imovelRepository.save(imovel);
    }
    public void desalocaImovel(Imovel imovel){
        imovel.setAtivo(false);
        imovelRepository.save(imovel);
    }
    public List<Imovel> listaImoveisDisponiveis(){
        return imovelRepository.findByAtivo(false);
    }
    public List<Imovel> listaImoveisDisponiveisPorTipo(TipoEnum tipo){
        List<Imovel> imoveisTipo = new ArrayList<>();
        listaImoveisDisponiveis().forEach(imovel -> {
            if(imovel.getTipo().equals(tipo));
                imoveisTipo.add(imovel);
        });
        return imoveisTipo;
    }
    public List<Imovel> listaImoveisDisponiveisComPrecoMaximo(double aluguelMax){
        List<Imovel> imoveis = new ArrayList<>();
        listaImoveisDisponiveis().forEach(imovel -> {
            if(imovel.getAluguelSugerido()<=aluguelMax)
                imoveis.add(imovel);
        });
        return imoveis;
    }
    public List<Imovel> listaImoveisDisponiveisPorBairro(String bairro){
        List<Imovel> imoveisBairro = new ArrayList<>();
        imovelRepository.findByAtivo(false).forEach(imovel->{
            if(imovel.getEndereco().getBairro().equals(bairro))
                imoveisBairro.add(imovel);
        });
        return imoveisBairro;
    }

}
