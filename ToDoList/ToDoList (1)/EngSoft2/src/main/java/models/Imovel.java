package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="imovel")
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TipoEnum tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    /*@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="imovel_locacao", joinColumns = {@JoinColumn(name="imovel_id")},
        inverseJoinColumns = {@JoinColumn(name="locacao_id")})
    private List<Locacao> locacao;*/
    private boolean isAtivo;
    private double metragem;
    private int dormitorio;
    private int banheiros;
    private int suites;
    private int vagasGaragem;
    @Column(name="aluguel_sugerido")
    private double aluguelSugerido;
    private String obs;

    public Imovel() {

    }

    public Imovel(TipoEnum tipo, Endereco endereco, double metragem, int dormitorio, int banheiros, int suites, int vagasGaragem, double aluguelSugerido, String obs) {
        this.tipo = tipo;
        this.endereco = endereco;
        this.isAtivo = false;
        this.metragem = metragem;
        this.dormitorio = dormitorio;
        this.banheiros = banheiros;
        this.suites = suites;
        this.vagasGaragem = vagasGaragem;
        this.aluguelSugerido = aluguelSugerido;
        this.obs = obs;
    }

    public Imovel(TipoEnum tipo, Endereco endereco, boolean ativo, double metragem, int dormitorio, int banheiros, int suites, int vagasGaragem, double aluguelSugerido, String obs) {
        this.tipo = tipo;
        this.endereco = endereco;
        this.isAtivo = ativo;
        this.metragem = metragem;
        this.dormitorio = dormitorio;
        this.banheiros = banheiros;
        this.suites = suites;
        this.vagasGaragem = vagasGaragem;
        this.aluguelSugerido = aluguelSugerido;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean ativo) {
        this.isAtivo = ativo;
    }

    public double getMetragem() {
        return metragem;
    }

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public int getDormitorio() {
        return dormitorio;
    }

    public void setDormitorio(int dormitorio) {
        this.dormitorio = dormitorio;
    }

    public int getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(int banheiros) {
        this.banheiros = banheiros;
    }

    public int getSuites() {
        return suites;
    }

    public void setSuites(int suites) {
        this.suites = suites;
    }

    public int getVagasGaragem() {
        return vagasGaragem;
    }

    public void setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public double getAluguelSugerido() {
        return aluguelSugerido;
    }

    public void setAluguelSugerido(double aluguelSugerido) {
        this.aluguelSugerido = aluguelSugerido;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
