package model.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.enums.StatusEmprestimo;

public class Emprestimo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Cliente cliente;

	private List<Livro> livrosEmprestados = new ArrayList<>();
	private LocalDate dataRetirada;
	private LocalDate dataDevolucaoPrevista;
	private StatusEmprestimo statusEmprestimo;

	
	
	public Emprestimo() {

	}

	public Emprestimo(int id, Cliente cliente, LocalDate dataRetirada, LocalDate dataDevolucaoPrevista) {

		this.id = id;
		this.cliente = cliente;
		this.dataRetirada = dataRetirada;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;

	}

	public int getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void addLivros(Livro livro) {
		livrosEmprestados.add(livro);
	}
	public List<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataDevolucaoPrevista() {

		return this.dataDevolucaoPrevista;
	}

	public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
	}

	public StatusEmprestimo getStatusEmprestimo() {
		if (this.verificarAtraso() && this.statusEmprestimo != this.statusEmprestimo.DEVOLVIDO) {

			this.statusEmprestimo = statusEmprestimo.ATRASADO;
		}
		return this.statusEmprestimo;
	}

	public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
		this.statusEmprestimo = statusEmprestimo;
	} 

	public boolean verificarAtraso() {
		Duration duration = Duration.between(dataDevolucaoPrevista.atStartOfDay(), LocalDate.now().atStartOfDay());
		int dias = (int) duration.toDays();

		return dias >= 1;
	}



	public void finalizarEmprestimo() {

		cliente.removerEmprestimo(this);

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Empréstimo do cliente: " + cliente.getNome() + "\n");
		sb.append("Data de Retirada: " + dataRetirada + "\n");
		sb.append("Data Prevista de Devolução: " + getDataDevolucaoPrevista() + "\n");
		sb.append("Status: " + getStatusEmprestimo() + "\n");
		sb.append("Livros emprestados:\n");
		for (Livro livro : livrosEmprestados) {
			sb.append(" - Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + "\n");
		}

		return sb.toString();
	}

}
