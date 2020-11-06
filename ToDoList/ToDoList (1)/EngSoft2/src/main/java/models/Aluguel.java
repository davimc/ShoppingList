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
}
