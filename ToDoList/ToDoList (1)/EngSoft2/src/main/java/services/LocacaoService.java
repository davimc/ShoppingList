package services;

import models.Imovel;
import models.TipoEnum;
import repositories.AluguelRepository;
import repositories.ClienteRepository;
import repositories.ImovelRepository;
import repositories.LocacaoRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LocacaoService {
    private ImovelRepository imovelRepository;
    private ClienteRepository clienteRepository;
    private LocacaoRepository locacaoRepository;
    private AluguelRepository aluguelRepository;

    public LocacaoService(EntityManager manager){
        imovelRepository = new ImovelRepository(manager);
        clienteRepository = new ClienteRepository(manager);
        locacaoRepository = new LocacaoRepository(manager);
        aluguelRepository = new AluguelRepository(manager);
    }

    public List<Imovel> listaImoveisDisponiveisDoTipo(TipoEnum tipo){
        return imovelRepository.findByAtivoPorTipo(false, tipo);
    }
}
