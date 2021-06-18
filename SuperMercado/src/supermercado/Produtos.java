package supermercado;

public abstract class Produtos {
    private String nome;
    private int quantidade;
    private float preco;
    
    public abstract void formaPag();

    public Produtos(String nome, int quantidade, float preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produtos{" + "nome=" + nome + ", quantidade=" + quantidade + ", preco=" + preco + '}';
    }

    
}
