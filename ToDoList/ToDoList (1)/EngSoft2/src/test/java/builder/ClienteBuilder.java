package builder;

import models.Cliente;

import java.time.LocalDate;

public class ClienteBuilder {
    private Cliente cliente;

    public ClienteBuilder() {
    }

    public static ClienteBuilder umCliente(){
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Cliente();
        builder.cliente.setCpf("60727289365");
        builder.cliente.setEmail("davimatosc@hotmail.com");
        builder.cliente.setNome("Davi Matos de Carvalho");
        builder.cliente.setTel1("982186943");
        builder.cliente.setNascimento(LocalDate.now());
        builder.cliente.setTel2("");

        return builder;
    }
    public ClienteBuilder comNome(String nome){
        cliente.setNome(nome);
        return this;
    }
    public ClienteBuilder comCpf(String cpf){
        cliente.setNome(cpf);
        return this;
    }
    public Cliente constroi(){
        return cliente;
    }
}
