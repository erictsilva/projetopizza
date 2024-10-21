package Projeto;

public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private double valorTotal;

    public Cliente(String nome, String endereco, String telefone, String email, double valorTotal) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.valorTotal = valorTotal;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public double getValorTotal() {
        return valorTotal;
    }
}