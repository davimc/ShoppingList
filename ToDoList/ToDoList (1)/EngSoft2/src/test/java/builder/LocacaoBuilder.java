package builder;

import models.Aluguel;
import models.Locacao;

import java.time.LocalDate;
import java.util.Arrays;

public class LocacaoBuilder {

    private Locacao locacao;

    public LocacaoBuilder() {
    }

    public static LocacaoBuilder umaLocacao(){
        LocacaoBuilder builder = new LocacaoBuilder();

        builder.locacao = new Locacao();
        builder.locacao.setCliente(ClienteBuilder.umCliente().constroi());
        builder.locacao.setImovel(ImovelBuilder.umImovel().comLocacao().constroi());
        builder.locacao.setAtivo(true);
        builder.locacao.setValorAluguel(800.0);
        builder.locacao.setPorcentualMulta(0.4);
        builder.locacao.setDataInicio(LocalDate.now());
        builder.locacao.setObs("");
        builder.locacao.setDataVencimento(LocalDate.now().plusYears(2));

        return builder;
    }
    public LocacaoBuilder com3MesesDeUso(){
        locacao.setDataInicio(LocalDate.now().minusMonths(3));
        locacao.setAlugueis(Arrays.asList(
                new Aluguel(locacao,locacao.getDataInicio().plusMonths(1),locacao.getDataInicio().plusMonths(1),locacao.getValorAluguel(),""),
                new Aluguel(locacao,locacao.getDataInicio().plusMonths(2),locacao.getDataInicio().plusMonths(2),locacao.getValorAluguel(),""),
                new Aluguel(locacao,locacao.getDataInicio().plusMonths(3),locacao.getDataInicio().plusMonths(3),locacao.getValorAluguel(),"")));
        return this;
    }
    public Locacao constroi(){
        return locacao;
    }
}
