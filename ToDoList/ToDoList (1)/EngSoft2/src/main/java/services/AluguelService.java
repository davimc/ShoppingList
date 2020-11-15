package services;

import models.Aluguel;
import models.Cliente;
import models.Locacao;
import repositories.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AluguelService {
    AluguelRepository repository;
    LocacaoService service;

    public AluguelService(EntityManager manager){
        repository = new AluguelRepository(manager);
        service = new LocacaoService(manager);
    }
    public void salvaAluguelPagamento(Aluguel aluguel){
        if(aluguel.getDataPagamento()==null)
            throw new IllegalArgumentException("Não houve pagamento");
        repository.save(aluguel);
    }
    public Aluguel geraAluguel(Locacao locacao, LocalDate dataVenciomento, String obs){
        Aluguel aluguel = new Aluguel(locacao,dataVenciomento,obs);
        repository.save(aluguel);
        return aluguel;
    }

    public List<Aluguel> alugueisPagos(Cliente cliente){
        List<Aluguel> todosAlugueisLocatario = new ArrayList<>();
        service.todasLocacoesPor(cliente).forEach(locacao->{
            todosAlugueisLocatario.addAll(locacao.getAlugueis());
        });
        return todosAlugueisLocatario;
    }
    public List<Aluguel> possuiPendencia(Locacao locacao){
        List<Aluguel> alugueisPendentes = new ArrayList<>();

        repository.findByLocacao(locacao).forEach(aluguel->{
            if(aluguel.getDataPagamento()==null)
                alugueisPendentes.add(aluguel);
        });
        return alugueisPendentes;
    }
}
