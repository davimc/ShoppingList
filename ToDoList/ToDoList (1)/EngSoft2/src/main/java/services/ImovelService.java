package services;

import models.Endereco;
import models.Imovel;
import models.TipoEnum;
import repositories.EnderecoRepository;
import repositories.ImovelRepository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

class ImovelService {
    private ImovelRepository imovelRepository;
    private EnderecoRepository enderecoRepository;

    public ImovelService(EntityManager manager){
        imovelRepository = new ImovelRepository(manager);
        enderecoRepository = new EnderecoRepository(manager);
    }
    public Imovel criaImovel(Imovel imovel){
        //só cria se o imovel ainda não foi cadastrado
        //só precisa verificar o endereço, pois não pode haver dois imoveis com o memso endereço
            try {
                System.out.println("Aqui foi");
                Optional<Endereco> endereco = enderecoRepository.findEndereco(imovel.getEndereco().getRua(), imovel.getEndereco().getNumero(), imovel.getEndereco().getBairro(), imovel.getEndereco().getCep());
                System.out.println("Aqui foi");
                if (endereco.isPresent())
                    throw new IllegalArgumentException("Imovel já criado");
            }catch (NoResultException e){
                e.getMessage();
            }
            enderecoRepository.save(imovel.getEndereco());
            imovelRepository.save(imovel);
            return imovelRepository.findByEndereco(
                    enderecoRepository.findEndereco(imovel.getEndereco().getRua(),imovel.getEndereco().getNumero(),imovel.getEndereco().getBairro(),imovel.getEndereco().getCep()).get().getId());

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

    public Imovel encontraImovel(Endereco endereco) {
            System.out.println("este é o id");
            return null;
       /* try {
            Endereco enderecoEncontrado = enderecoRepository.findEndereco(endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCep()).get();
            return imovelRepository.findByEndereco(enderecoEncontrado.getId()).get();
        }catch (NoResultException e){
            throw new NoResultException("Endereco ou imovel não encontrado(s)");
        }*/
    }
}
