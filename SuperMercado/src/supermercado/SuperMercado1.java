package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SuperMercado1 {

	private ArrayList<Produtos> produtos;


	public SuperMercado1( ) {
		this.produtos = new ArrayList<Produtos>();
	}

	public void adicionaItem(Produtos prod) {
		this.produtos.add(prod);
	}
  
  public void listarItens() {
    for(Produtos prod:produtos) {
          System.out.println(prod.toString());
    }
    System.out.println("Total = " + this.produtos.size() + " Produtos do mercadinho estão em lista!\n");
	}
  
  public void excluirItem(Itens prod) {
		if (this.produtos.contains(prod)) {
			this.produtos.remove(prod);
			System.out.println("[Item " + mani.toString() + "foi retirado da lista]\n");
    }
    else
			System.out.println("Item inexistente!\n");
	}
  
  public void excluirItens() {
		produtos.clear();
		System.out.println("Itens excluidos com sucesso!\n");
	}
  public void gravarItens()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("C:\Users\daviw\Documents"));
			for(Produtos prod:produtos) {
				outputStream.writeObject(prod);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
  public void recuperarItens() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("C:\Users\daviw\Documents"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Bebidas)  
					this.produtos.add((Bebidas)obj);
				else if (obj instanceof Guloseimas)  
					this.mamiferos.add((Guloseimas)obj);
			}
    }catch (EOFException ex) {
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Itens recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
  public static void main(String[] args) {
		SuperMercado1 merc  = new SuperMercado1();

		Bebida suco    = new Bebida("Suco",   3, "Maria");
		Gato jujuba= new Guloseimas("Jujuba", 7, "Maria");
		Cao  cha     = new Cao ("Chá",  2, "Jose");
		Cao  bala     = new Guloseimas ("Bala", 5, "Jose");
		merc.adicionaItem(suco);
		merc.adicionaItem(jujuba);
		merc.adicionaItem(cha);
		merc.adicionaItem(bala);
		merc.listarItens();
		merc.gravarItens();
		merc.excluirItem(bala);
		merc.listarItens();
		merc.excluirItens();
		merc.listarItens();
		merc.recuperarItens();
		merc.listarItens();
	}

}
