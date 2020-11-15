package builder;
import models.Endereco;
import models.Imovel;
import models.TipoEnum;

public class ImovelBuilder {
    private Imovel imovel;

    public ImovelBuilder() {
    }
    public static ImovelBuilder umImovel(){
        ImovelBuilder builder = new ImovelBuilder();
        builder.imovel = new Imovel();
        builder.imovel.setTipo(TipoEnum.APARTAMENTO);
        builder.imovel.setEndereco(new Endereco("Rua dos Prazeres","537","Centro","65020-460"));
        builder.imovel.setAluguelSugerido(800);
        builder.imovel.setBanheiros(2);
        builder.imovel.setDormitorio(4);
        builder.imovel.setVagasGaragem(3);
        builder.imovel.setSuites(1);
        builder.imovel.setMetragem(55.5);
        builder.imovel.setAtivo(false);

        return builder;
    }
    public ImovelBuilder comLocacao(){
        this.imovel.setAtivo(true);
        return this;
    }
    public ImovelBuilder comEndereco(String rua, String num,String bairro, String cep){
        this.imovel.setEndereco(new Endereco(rua,num,bairro,cep));

        return this;
    }
    public ImovelBuilder comTipo(TipoEnum tipo){
        imovel.setTipo(tipo);
        return this;
    }
    public ImovelBuilder comAluguelSugerido(double aluguel){
        this.imovel.setAluguelSugerido(aluguel);
        return this;
    }
    public Imovel constroi(){
        return imovel;
    }
}
