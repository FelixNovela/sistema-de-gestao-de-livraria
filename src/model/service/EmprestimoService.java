package model.service;

import java.util.List;

import crud.Crud;
import model.entities.Cliente;
import model.entities.Emprestimo;
import model.entities.Livro;

public class EmprestimoService {
	

	public boolean realizarEmprestimo(int[] livro, Cliente cliente, Emprestimo emp, List<Cliente> clientes) {
		
		if (livro.length <= 0) {
			System.out.println("Quantidade minima de livros emprestados deve ser igual a 1");
			return false;
		}
		if (livro.length + cliente.verificarQuantidadeDeLivros() > 3) {

			System.out.println(cliente.getNome()+ ": Emprestimo nao permitido. Nao pode ter mais de  3 livros em empréstimos ativos.");
			return false;
		}
		Crud crudLivros = new Crud("livros.dat");
		Crud crudClientes = new Crud("clientes.dat");

		List<Cliente> listaC = clientes;
		List<Livro> listaL = crudLivros.listarObj();
		
		for (int i = 0; i < livro.length; i++) {
			for (int j = 0; j < listaL.size(); j++) {
				if (String.valueOf(livro[i]) == listaL.get(j).getIsbn()) {
					System.out.println("Livros emprestados");
					listaL.get(j).diminuirEstoque();
					crudLivros.atualizar(listaL, j);
					emp.addLivros(listaL.get(j));
	
					cliente.adicionarEmprestimo(emp); 
					
				}
			}
		}
		crudClientes.atualizar(listaC, 1);
		return true;

	}
}
