package services;

import models.*;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    private LocacaoService locacaoService;
    private AluguelService aluguelService;

    public MainService(EntityManager manager){
        locacaoService = new LocacaoService(manager);
        aluguelService = new AluguelService(manager);
    }
    public Locacao realizaLocacao(Cliente cliente,Imovel imovel, double valor, double multa, String obs){
        if(multa>1.0)
            throw new IllegalArgumentException("Multa acima de 100%");
        return locacaoService.locarImovel(cliente,imovel,valor,multa,obs);
    }
    public void cobraAluguel(String cpf, Endereco endereco, LocalDate data){
        //Locacao locacao = locacaoService.encontraLocacao(cpf,endereco);
    }
    public List<Aluguel> listaAlugueisPagosCliente(String cpf) {

        List<Aluguel> alugueisCliente = new ArrayList<>();
        List<Locacao> locacoesCliente = locacaoService.listaLocacaoDeUmCliente(cpf);
        locacoesCliente.forEach(locacao-> {
            locacao.getAlugueis().forEach(aluguel -> {
                if (aluguel.getDataPagamento() != null)
                    alugueisCliente.add(aluguel);
            });
        });
        return alugueisCliente;
    }


}
