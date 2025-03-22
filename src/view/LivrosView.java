package view;

import java.util.List;

import model.entities.Livro;

public class LivrosView {
	public void Menu() {
		System.out.println("------------------------|");
		System.out.println("    GERENCIAR LIVROS    |");
		System.out.println("--------- MENU ---------|");
		System.out.println("1. Adicionar Livros     |");
		System.out.println("2. Listar Livros        |");
		System.out.println("3. Atualizar Quantidade |");
		System.out.println("4. Remover Livro        |");
		System.out.println("5. Voltar               |");
		System.out.println("6. Sair                 |");
		System.out.println("------------------------|");
		System.out.print("Escolha uma opcao:");
		
	}
	
	public void listarLivros(List<Livro> livros) { 
		for(Livro lista : livros) {
			if(lista.isAtivo() == true) {
				System.out.println(lista);
			}
			
		}
	}
}
