package supermercado;

public abstract class Produtos {
    private String nome;
    private int quantidade;
    private float preco;
    protected String categoria;
    
    public abstract String formaPag();

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        String produto = "";
        produto += "Nome: " + this.nome + "\n";
        produto += "Quantidade: " + this.quantidade + "\n";
        produto += "Preço: R$ " + this.preco + "\n";
        produto += "Categoria: " + this.categoria + "\n";
        produto += "Forma de Pagamento: " + formaPag();
        return produto;
    }

    

}
