package services;

import models.*;
import repositories.AluguelRepository;
import repositories.ClienteRepository;
import repositories.ImovelRepository;
import repositories.LocacaoRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoService {
    private ImovelService imovelService;
    private ClienteService clienteService;
    private AluguelService aluguelService;
    private LocacaoRepository locacaoRepository;

    public LocacaoService(EntityManager manager){
        imovelService = new ImovelService(manager);
        clienteService = new ClienteService(manager);
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

        clienteService.salva(cliente);
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

}
