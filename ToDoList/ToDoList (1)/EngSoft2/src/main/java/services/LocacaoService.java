package services;

import models.*;
import repositories.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;


class LocacaoService {
    private ClienteService clienteService;
    private LocacaoRepository locacaoRepository;


    private AluguelService aluguelService;
    private ImovelService imovelService;

    public LocacaoService(EntityManager manager){
        imovelService = new ImovelService(manager);
        clienteService = new ClienteService(manager);
        //aluguelService = new AluguelService(manager);
        locacaoRepository = new LocacaoRepository(manager);

    }

    public Locacao locarImovel(Locacao locacao){
        this.locacaoValida(locacao.getCliente(),locacao.getImovel(), locacao.getValorAluguel());
        locacao.setCliente(clienteService.salva(locacao.getCliente()));
        locacao.setImovel(imovelService.criaImovel(locacao.getImovel() ));
        //locacao.adicionaAluguel(aluguelService.geraAluguel(locacao,LocalDate.now().plusMonths(1),"primeiro aluguel"));
        Cliente cliente = clienteService.encontraPorCpf(locacao.getCliente().getCpf());
        System.out.println(locacao.getImovel());
        Imovel imovel = imovelService.encontraImovel(locacao.getImovel().getEndereco());
        /*System.out.println("Cliente algo "+imovel.getAluguelSugerido());
        locacaoRepository.save(locacao);
        try {
            locacao = locacaoRepository.findLocacao(locacao.getCliente(), locacao.getImovel()).get();
        }catch(NoResultException e){
            e.getMessage();
        }
            imovelService.alocaImovel(locacao.getImovel());*/
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
    public void adicionaAluguel(Locacao locacao, LocalDate dataVencimento, String obs){
        Aluguel aluguel = aluguelService.geraAluguel(locacao,dataVencimento,"");
        locacao.adicionaAluguel(aluguel);
        locacaoRepository.save(locacao);
    }
   /* public double pagaAluguel(Locacao locacao, Aluguel aluguel, LocalDate dataPagamento){

    }*/

    public List<Locacao> listaLocacaoDeUmCliente(String cpf) {
        Cliente cliente = clienteService.encontraPorCpf(cpf);
        return locacaoRepository.listByCliente(cliente);
    }
    public Locacao encontraLocacao(String cpf, String rua,String numero, String bairro, String cep) {
        Cliente cliente = clienteService.encontraPorCpf(cpf);
        Imovel imovel;

        imovel = imovelService.encontraImovel(new Endereco(rua,numero,bairro,cep));
        Locacao locacao = locacaoRepository.findLocacao(cliente, imovel);
        return locacao;

    }

    public Aluguel cadastraPagamento(Locacao locacao, double valor) {
        Aluguel aluguelPendente = null;
        if(locacao.getValorAluguel()>=valor)
            throw new IllegalArgumentException("Valor pago não pode ser menor que o valor do boleto");
        for (int i =0; i<locacao.getAlugueis().size(); i++) {
                if(locacao.getAlugueis().get(i).getDataPagamento()==null){
                    locacao.getAlugueis().get(i).setDataPagamento(LocalDate.now());
                    locacao.getAlugueis().get(i).setValorPago(valor);
                    aluguelService.salvaAluguelPagamento(locacao.getAlugueis().get(i));
                    locacaoRepository.save(locacao);
                    return locacao.getAlugueis().get(i);
                }
        }
        return null;
    }
}
