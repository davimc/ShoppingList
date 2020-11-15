package models;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

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

    public Aluguel(Locacao locacao, LocalDate dataVencimento, String obs) {
        this.locacao = locacao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = null;
        this.valorPago = 0.0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Double.compare(aluguel.valorPago, valorPago) == 0 &&
                locacao.equals(aluguel.locacao) &&
                dataVencimento.equals(aluguel.dataVencimento) &&
                Objects.equals(dataPagamento, aluguel.dataPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locacao, dataVencimento, dataPagamento, valorPago);
    }
}
