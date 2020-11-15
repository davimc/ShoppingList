package services;

import models.*;
import repositories.ClienteRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainService {

    private LocacaoService locacaoService;


    public MainService(EntityManager manager){

        locacaoService = new LocacaoService(manager);

    }

    public Locacao realizaLocacaoComPrimeiroAluguel(Cliente cliente,Imovel imovel, double valor, double multa, String obs){
        if(multa>1.0)
            throw new IllegalArgumentException("Multa acima de 100%");
        Locacao locacao = new Locacao(imovel,cliente,valor,multa,LocalDate.now(),LocalDate.now().plusYears(2),obs);
        return locacaoService.locarImovel(locacao);
    }
    public void cadastraAluguel(Locacao locacao, LocalDate dataVencimento,String obs){
        locacaoService.adicionaAluguel(locacao,dataVencimento,obs);
    }
    public void pagaAluguel(Locacao locacao, double valor){
        locacaoService.cadastraPagamento(locacao,valor);
    }
    public List<Aluguel> listaAlugueisPagosCliente(String cpf) {

        List<Aluguel> alugueisCliente = new ArrayList<>();
        List<Locacao> locacoesCliente = locacaoService.listaLocacaoDeUmCliente(cpf);
        System.out.println("okok");
        locacoesCliente.forEach(locacao-> {
        System.out.println("okok");
            locacao.getAlugueis().forEach(aluguel -> {
                if (aluguel.getDataPagamento() != null)
                    alugueisCliente.add(aluguel);
            });
        });
        return alugueisCliente;
    }


}
