package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="locacao")
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="imovel_id")
    private Imovel imovel;
    @OneToMany(mappedBy = "locacao", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluguel> alugueis = new ArrayList<>();

    private double valorAluguel;
    private double porcentualMulta;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataVencimento;
    private boolean ativo;
    private String obs;

    public Locacao() {
    }

    public Locacao(Imovel imovel, Cliente cliente, double valorAluguel, double porcentualMulta, LocalDate dataInicio, LocalDate dataVencimento, String obs) {
        this.imovel = imovel;
        this.cliente = cliente;
        this.valorAluguel = valorAluguel;
        this.porcentualMulta = porcentualMulta;
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.dataVencimento = dataVencimento;
        this.ativo = true;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public double getPorcentualMulta() {
        return porcentualMulta;
    }

    public void setPorcentualMulta(double porcentualMulta) {
        this.porcentualMulta = porcentualMulta;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public void adicionaAluguel(Aluguel aluguel){
        alugueis.add(aluguel);
    }


}
