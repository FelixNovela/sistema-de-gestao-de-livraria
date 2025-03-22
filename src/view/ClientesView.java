package view;

import java.util.List;

import model.entities.Cliente;

public class ClientesView {
	public void Menu() {
		System.out.println("-------------------------------|");
		System.out.println("      GERENCIAR CLIENTES       |");
		System.out.println("--------- MENU ----------------|");
		System.out.println("1. Registrar novo cliente      |");
		System.out.println("2. Listar clientes             |");
		System.out.println("3. Atualizar dados do cliente  |");
		System.out.println("4. Remover cliente             |");
		System.out.println("5. Alterar estado do cliente   |");
		System.out.println("6. Pesquisar cliente           |");
		System.out.println("7. Voltar                      |");
		System.out.println("8. Sair                        |");
		System.out.println("-------------------------------|");
		System.out.print("Escolha uma opcao:");
		
	} 
	
	public void listarClientes(List<Cliente> clientes) {
		List<Cliente> lista = clientes;
		System.out.println("LISTA DE CLIENTES");
		for(Cliente listClientes : lista) {
			System.out.println(listClientes);
		}
	}
}
