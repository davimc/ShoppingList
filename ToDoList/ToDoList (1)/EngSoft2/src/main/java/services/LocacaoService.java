package services;

import models.*;
import repositories.ClienteRepository;
import repositories.ImovelRepository;
import repositories.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class LocacaoService {
    private ImovelService imovelService;
    private ClienteRepository clienteRepository;
    private AluguelService aluguelService;
    private LocacaoRepository locacaoRepository;

    public LocacaoService(EntityManager manager){
        imovelService = new ImovelService(manager);
        clienteRepository = new ClienteRepository(manager);
        aluguelService = new AluguelService(manager);
        locacaoRepository = new LocacaoRepository(manager);

    }

    public Locacao locarImovel(Cliente cliente, Imovel imovel, double valor,double multa, String obs){
        this.locacaoValida(cliente,imovel, valor);
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setImovel(imovel);
        locacao.setAtivo(true);
        locacao.setDataInicio(LocalDate.now());
        locacao.setDataVencimento(locacao.getDataInicio().plusYears(2));
        locacao.setPorcentualMulta(multa);
        locacao.setObs(obs);

        clienteRepository.save(cliente);
        imovelService.alocaImovel(imovel);
        locacaoRepository.save(locacao);

        return locacao;
    }
    public void finalizaLocacao(Locacao locacao){
        locacao.setAtivo(false);
        locacao.setDataFim(LocalDate.now());
        imovelService.desalocaImovel(locacao.getImovel());


        locacaoRepository.save(locacao);
    }
    public List<Locacao> todasLocacoesPor(Cliente cliente){
        return locacaoRepository.listByCliente(cliente);
    }
    public List<Locacao> todasLocacoesPor(Imovel imovel){
        return locacaoRepository.listByImovel(imovel);
    }


    private boolean valorAluguelPermitido(Imovel imovel,double valor){
        return imovel.getAluguelSugerido()>=valor;
    }
    private void locacaoValida(Cliente cliente, Imovel imovel, double valor){
        if (cliente==null)
            throw new IllegalArgumentException("Cliente não pode estar nulo");
        if(imovel.isAtivo()==true)
            throw new IllegalStateException("Imovel já está alocado");
        Locacao locacao = new Locacao();
        if (valorAluguelPermitido(imovel, valor))
            locacao.setValorAluguel(valor);
        else
            throw new IllegalArgumentException("Valor não pode ser menor que o sugerido");

    }
    public void pagaAluguel(Locacao locacao, Aluguel aluguel){

    }

    public List<Locacao> listaLocacaoDeUmCliente(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return locacaoRepository.listByCliente(cliente);
    }
    public Locacao encontraLocacao(String cpf, String rua,String numero, String bairro, String cep) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        Imovel imovel;
        try{
             imovel = imovelService.encontraImovel(rua,numero,bairro,cep);
             return locacaoRepository.findLocacao(cliente, imovel).get();
        }catch (NoResultException e){
            e.getMessage();
            return null;
        }
    }
}
