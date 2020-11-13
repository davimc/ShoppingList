package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
/*    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="cliente_locacao",
            joinColumns = {@JoinColumn(name="cliente_id")},
            inverseJoinColumns = {@JoinColumn(name="locacao_id")})
    private List<Locacao> locacao;*/

    private String nome;
    private String cpf;
    private String email;
    private String tel1;
    private String tel2;
    private LocalDate nascimento;

    public Cliente() {
    }

    public Cliente( String nome, String cpf, String email, String tel1, String tel2, LocalDate nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome) &&
                cpf.equals(cliente.cpf) &&
                Objects.equals(nascimento, cliente.nascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, nascimento);
    }
}
