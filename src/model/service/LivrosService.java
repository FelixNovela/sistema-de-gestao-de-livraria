package model.service;

import java.util.List;

import model.entities.Livro;

public class LivrosService {
	public boolean validarAdicaoLivro(Livro livro, List<Livro> livros) {

		if (livro.getEstoque() <= 0) {

			System.out.println("Erro: Nao e possivel adicionar o livro " + livro.getTitulo()
					+ " (quantidade menor ou igual a zero)");
			return false;

		}

		if (livro.getTitulo().trim().isEmpty() || livro.getAutor().trim().isEmpty()) {
			System.out.println("Livro com título ou autor vazio");
			return false;
		}

		if (validarIsbn(livro.getIsbn(), livros) != -1) {
			System.out.println("Livro com ISBN ja existente " + livro.getIsbn());
			return false;
		}
 
		return true;
	}

	public void removerLivro() {

	}

	public int validarIsbn(int isbn, List<Livro> livros) {
		for (int i = 0; i < livros.size(); i++) {
			if (livros.get(i).getIsbn() == isbn) {
				return i;
			}
		}

		return -1;
	}

}
