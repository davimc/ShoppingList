package services;

import models.Endereco;
import models.Imovel;
import models.TipoEnum;
import repositories.EnderecoRepository;
import repositories.ImovelRepository;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

class ImovelService {
    private ImovelRepository imovelRepository;
    private EnderecoRepository enderecoRepository;

    public ImovelService(EntityManager manager){
        imovelRepository = new ImovelRepository(manager);
        enderecoRepository = new EnderecoRepository(manager);
    }
    public void criaImovel(TipoEnum tipo, Endereco endereco, double metragem,double aluguelSugerido,int nBanheiro,int nSuite, int nGaragem, int nDormitorio, String obs){
        if(enderecoRepository.findEndereco(endereco.getRua(),endereco.getNumero(),endereco.getBairro(),endereco.getCep()).isPresent())
            throw new DuplicateFormatFlagsException("Este endereco já está presente em um imovel");
        enderecoRepository.save(endereco);
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

    public Imovel encontraImovel(String rua, String numero, String bairro, String cep) {
        Endereco endereco = enderecoRepository.findEndereco(rua,numero,bairro,cep).get();
        return imovelRepository.findByEndereco(endereco.getId()).get();
    }
}
