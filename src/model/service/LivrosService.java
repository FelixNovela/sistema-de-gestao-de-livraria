package model.service;

import java.util.List;

import crud.Crud;
import model.entities.Livro;

public class LivrosService {
	private Crud crud = new Crud("livros.dat");
	private List<Livro> listaLivros;

	private boolean validarDadosDoLivro(Livro livro) {
		if (livro.getEstoque() <= 0) {

			System.out.println("Erro: Nao e possivel adicionar o livro " + livro.getTitulo()
					+ " (quantidade menor ou igual a zero)");
			return false;

		}

		if (livro.getTitulo().trim().isEmpty() || livro.getAutor().trim().isEmpty()) {
			System.out.println("Erro ao adicionar livro: Livro com título ou autor vazio");
			return false;
		}

		return true;
	}

	public void adicionarLivro(Livro livro) {
		if (validarDadosDoLivro(livro)) {
			crud.novoObjecto(livro);
		}
	}

	public List<Livro> listarLivros() {
		return crud.listarObj();
	}

	public void atulizarQuantidadeLivros(String isbn, int quantidade) {
		listaLivros = crud.listarObj();
		int indice = verificarIsbn(isbn);
		if (indice != -1) {
			listaLivros.get(indice).aumentarEstoque(quantidade);
			crud.atualizar(listaLivros, indice);
		} else {
			System.out.println("Falha ao atualizar quantidade no stock, ISBN incorreto");
		}

	}

	public Livro pesquisarLivro(String isbn) {
		listaLivros = crud.listarObj();
		int indice = verificarIsbn(isbn);
		
		if (!(listaLivros.get(indice).getIsbn().equalsIgnoreCase(isbn))){
			System.out.println("Livro nao encontrado");
			return null;
		}
		return listaLivros.get(indice);
	}

	public void removerLivro(String isbn) {
		listaLivros = crud.listarObj();
		int indice = verificarIsbn(isbn);
		if (indice != -1) {
			if (listaLivros.get(indice).getTotalEmprestado() != 0) {
				System.out.println("Nao pode remover um livro que esta associado a emprestimos ativos");
			} else {
				listaLivros.get(indice).setAtivo(false);
				crud.atualizar(listaLivros, indice);
			}
		} else {
			System.out.println("ISBN invalido");
		}
	}

	public int verificarIsbn(String isbn) {
		listaLivros = crud.listarObj();
		for (int i = 0; i < listaLivros.size(); i++) {
			if (listaLivros.get(i).getIsbn().equalsIgnoreCase(isbn)) {
				return i;
			}
		}

		return -1;
	}

}
