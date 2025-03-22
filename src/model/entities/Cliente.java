package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.entities.enums.StatusEmprestimo;

public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String nome;
	private String numeroDeIdentificacao;
	private String contato;

	private List<Emprestimo> emprestimosAtivos = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(String nome, String numeroDeIdentificacao, String contato) {
		this.isbn = gerarIdUnico(nome);
		this.nome = nome;
		this.numeroDeIdentificacao = numeroDeIdentificacao;
		this.contato = contato;
	}

	public String getIsbn() {
		return isbn;
	}

	private String gerarIdUnico(String nome) {
		Random random = new Random();
		int numeros = random.nextInt(900) + 100;
		char inicial = Character.toUpperCase(nome.charAt(0));
		return numeros + "" + inicial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getnumeroDeIdentificacao() {
		return numeroDeIdentificacao;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public List<Emprestimo> getEmprestimosAtivos() {
		return emprestimosAtivos;
	}

	public void adicionarEmprestimo(Emprestimo emprestimo) {
		emprestimo.setStatusEmprestimo(StatusEmprestimo.ATIVO); 
		emprestimosAtivos.add(emprestimo);
	}

	public void removerEmprestimo(Emprestimo emprestimo) {
		emprestimo.setStatusEmprestimo(StatusEmprestimo.DEVOLVIDO);
		for (int i = 0; i < emprestimo.getLivrosEmprestados().size(); i++) {
			emprestimo.getLivrosEmprestados().get(i).aumentarEstoque(1);
			emprestimo.getLivrosEmprestados().get(i).setTotalEmprestado(1);

		}
		System.out.println(this.getNome() + " Finalizou o emprestimo: \n" + emprestimo.toString() +(emprestimo.verificarAtraso() ? " ATRASO NA DEVOLUCAO \n ": "\n"));
	}

	public String listarEmprestimos() {
		StringBuilder sb = new StringBuilder();
		for (Emprestimo emprestimo : emprestimosAtivos) {
			sb.append(emprestimo.toString()).append("\n");
		}
		return sb.toString();
	}

	public int verificarQuantidadeDeLivros() {
		int totalLivros = 0;
		
		for (Emprestimo emp : emprestimosAtivos) {
			totalLivros += emp.getLivrosEmprestados().size();
		}
		return totalLivros; 
	}

	@Override
	public String toString() {
		return "ID: " + isbn +" | Cliente: " + nome + " | Numero De Identificacao: " + numeroDeIdentificacao + " | Contato: " + contato
				+ " | Empréstimos ativos: " + emprestimosAtivos.size();
	}
}
