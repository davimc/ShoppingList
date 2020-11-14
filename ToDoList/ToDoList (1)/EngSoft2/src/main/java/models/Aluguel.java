package models;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="aluguel_id")
    private Locacao locacao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valorPago;
    private String obs;

    public Aluguel() {
    }

    public Aluguel(Locacao locacao, LocalDate dataVencimento, LocalDate dataPagamento, double valorPago, String obs) {
        this.locacao = locacao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.obs = obs;
    }

    public Long getId() {
        return id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    /*public Aluguel aluguelPendente(){

    }*/

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
