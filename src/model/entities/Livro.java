package model.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	private int isbn;
	private String titulo;
	private String autor;
	private LocalDate DateanoPublicacao;
	private int estoque;
	private int totalEmprestado;
	private boolean ativo;

	public Livro() {

	}

	public Livro(int isbn, String titulo, String autor, LocalDate dateanoPublicacao, int estoque) {

		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.DateanoPublicacao = dateanoPublicacao;
		this.estoque = estoque;
		this.ativo = true;

	}

	public int getIsbn() {
		return isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getDateanoPublicacao() {
		return DateanoPublicacao;
	}

	public void setDateanoPublicacao(LocalDate dateanoPublicacao) {
		DateanoPublicacao = dateanoPublicacao;
	}

	public int getTotalEmprestado() {
		return totalEmprestado;
	}

	public void setTotalEmprestado(int totalEmprestado) {
		this.totalEmprestado -= totalEmprestado;
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getEstoque() {
		return estoque;
	}

	public void aumentarEstoque(int quantidade) {
		this.estoque += quantidade;

	} 

	public boolean diminuirEstoque() {
		if (this.estoque <= 0) {
			System.out.println("Operacao nao permitida. Quantidade disponivel " + getEstoque() + "\n");
			return false;
		}
		this.estoque -= 1;
		this.totalEmprestado += 1;
		return true; 

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " + getIsbn() + " | ");
		sb.append("Titulo: " + getTitulo() + " | ");
		sb.append("Autor: " + getAutor() + " | ");
		sb.append("Data de Publicacao: " + getDateanoPublicacao() + " | ");
		sb.append("Quantidade no estoque: " + getEstoque() + " | ");
		sb.append("Total emprestados.: " + getTotalEmprestado() + " | ");

		return sb.toString();
	}

}
