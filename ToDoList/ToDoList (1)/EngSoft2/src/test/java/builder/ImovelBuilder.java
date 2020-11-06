package builder;

import models.Endereco;
import models.Imovel;

public class ImovelBuilder {
    private Imovel imovel;

    public ImovelBuilder() {
    }
    public static ImovelBuilder umImovel(){
        ImovelBuilder builder = new ImovelBuilder();
        builder.imovel = new Imovel();
        builder.imovel.setEndereco(new Endereco("Rua dos Manacas",7,"São Francisco","65076210"));
        builder.imovel.setAluguelSugerido(800);
        builder.imovel.setBanheiros(2);
        builder.imovel.setDormitorio(4);
        builder.imovel.setVagasGaragem(3);
        builder.imovel.setSuites(1);
        builder.imovel.setMetragem(55.5);
        builder.imovel.setAtivo(false);

        return builder;
    }
    public ImovelBuilder comAlocacao(){
        this.imovel.setAtivo(true);
        return this;
    }
    public ImovelBuilder comEndereco(String rua, int num,String bairro, String cep){
        this.imovel.setEndereco(new Endereco(rua, num,bairro, cep));

        return this;
    }

    public Imovel constroi(){
        return this.imovel;
    }
}
