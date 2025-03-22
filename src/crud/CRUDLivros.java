package crud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.entities.Livro;

public class CRUDLivros {

	private static String ARQUIVO = "livros.dat";

	public void adicionarLivros(Livro livro) throws IllegalArgumentException {
		List<Livro> livros = listarLivros();

		if (livro.getEstoque() <= 0) {

			throw new IllegalArgumentException(
					"Erro: Nao e possivel adicionar o livro " + livro.getTitulo() + " (quantidade menor ou igual a zero)");

		}

		if (livro.getTitulo().trim().isEmpty() || livro.getAutor().trim().isEmpty()) {
			throw new IllegalArgumentException("Livro com título ou autor vazio");

		}

		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getIsbn() == livro.getIsbn()) {
				throw new IllegalArgumentException("Livro com ID ja existente " + livro.getIsbn());
			}

		}
		livros.add(livro);
		salvarLivros(livros);

	}

	public void atualizarLivro(int id, int quantidade) {
		List<Livro> livros = listarLivros();
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getIsbn() == id) {
				livros.get(i).aumentarEstoque(quantidade);
			}
		}
		salvarLivros(livros);
	}

	public boolean removerLivro(int id) {
		List<Livro> livros = listarLivros();
		int resultado = 0;
		for(int i = 0; i < livros.size(); i++) {
			if(livros.get(i).getIsbn() == id) {
				resultado++;
	 			
			}
			if(resultado != 0) {
				livros.get(i).setAtivo(false);
				break;
				
			}
		}
		if(resultado == 0) {
			System.out.println("ID nao existe");
			return false;
		}
		salvarLivros(livros);
		return true; 

	}

	
	public List<Livro> listarLivros() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
			return (List<Livro>) ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Sem livros disponiveis");
			return new ArrayList<>();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<Livro>();
		}
	}

	public void salvarLivros(List<Livro> livros) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
			oos.writeObject(livros);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
