package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SuperMercado {
    private ArrayList<Produtos> produtos;
    
    public SuperMercado(){
        this.produtos = new ArrayList<Produtos>();        
    }
    public String[] leValores (String [] dadosIn){
	String [] dadosOut = new String [dadosIn.length];

	for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");
            return dadosOut;
    }
    public Bebidas leBebidas(){
        String [] valores = new String [3];
	String [] nomeVal = {"Nome", "Quantidade", "Preço", "Categoria"};
	valores = leValores (nomeVal);
        int quantidade = this.retornaInteiro(valores[1]);
	Bebidas bebidas = new Bebidas (valores[0],quantidade,valores[2]);
            return bebidas;
    }
    
    public Guloseimas leGuloseimas(){
        String [] valores = new String [3];
	String [] nomeVal = {"Nome", "Quantidade", "Preço", "Categoria"};
	valores = leValores (nomeVal);
        int quantidade = this.retornaInteiro(valores[1]);

	Guloseimas guloseimas = new Bebidas (valores[0],quantidade,valores[2]);
            return guloseimas;
    }
    private boolean intValido(String s) {
	try {
            Integer.parseInt(s); // Metodo estatico, que tenta tranformar uma string em inteiro
		return true;
	} catch (NumberFormatException e) { // Nao conseguiu tranformar em inteiro e gera erro
            return false;
	}
    }
    public int retornaInteiro(String entrada) { // retorna um valor inteiro
	int numInt;
		//Enquanto nao for possivel converter o valor de entrada para inteiro, permanece no loop
            while (!this.intValido(entrada)) {
		entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
            }
		return Integer.parseInt(entrada);
	}
    public void salvaProdutos (ArrayList<Produtos> produtos){
		ObjectOutputStream outputStream = null;
		try {
                    outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\petStore.dados"));
                    for (int i=0; i < produtos.size(); i++)
			outputStream.writeObject(produtos.get(i));
		} catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
                    ex.printStackTrace();
		} catch (IOException ex) {
                    ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
                    try {
			if (outputStream != null) {
                            outputStream.flush();
                            outputStream.close();
			}
			} catch (IOException ex) {
                            ex.printStackTrace();
			}
		}
	}
    @SuppressWarnings("finally")
    public ArrayList<Produtos> recuperaProdutos (){
            ArrayList<Produtos> produtosTemp = new ArrayList<Produtos>();
            ObjectInputStream inputStream = null;         
		try {
                    inputStream = new ObjectInputStream
			(new FileInputStream("c:\\temp\\petStore.dados"));
                    Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
                            if (obj instanceof Produtos) {
				produtosTemp.add((Produtos) obj);
                            }   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com produtos não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return produtosTemp;
		}
	}
    public void menuProdutos (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Produtos\n" +
					"Opções:\n" + 
					"1. Inserir Produtos\n" +
					"2. Exibir Produtos\n" +
					"3. Limpar Produtos\n" +
					"4. Gravar Produtos\n" +
					"5. Recuperar Produtos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Produtos\n" +
						"Opções:\n" + 
						"1. Bebidas\n" +
						"2. Guloseimas\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: produtos.add((Produtos)leBebidas());
				break;
				case 2: produtos.add((Produtos)leGuloseimas());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Produto para entrada não inserido!");
				}

				break;
			case 2: // Exibir dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < produtos.size(); i++)	{
					dados += produtos.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				produtos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				salvaProdutos(produtos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				produtos = recuperaProdutos();
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo SuperMercado");
				break;
			}
		} while (opc1 != 9);
	}
    public static void main(String[] args) {        
        SuperMercado merc = new SuperMercado();
        merc.menuProdutos();
    }
}
