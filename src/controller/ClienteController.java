package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import crud.Crud;
import model.entities.Cliente;
import model.entities.Emprestimo;
import model.entities.Livro;
import model.service.EmprestimoService;
import model.service.ClienteService;
import view.ClientesView;

public class ClienteController {
	private Cliente cliente;
	private ClientesView clientesView;

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void gerenciarCliente() {
		int op;
		do {
			Crud crudCliente = new Crud("clientes.dat");
		
			
			Scanner sc = new Scanner(System.in);

			this.clientesView = new ClientesView();
			clientesView.Menu();
			op = sc.nextInt();
			sc.nextLine();

			switch (op) {
			case 1:

				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("Numero do documento de identificacao: ");
				String numeroId = sc.nextLine();
				System.out.println("Telefone: ");
				String telefone = sc.nextLine();
				crudCliente.novoObjecto(new Cliente(nome, numeroId, telefone));
				break;
			case 2:
				clientesView.listarClientes(crudCliente.listarObj());
				break;
			case 3:
				System.out.println("REALIZAR EMPRESTIMO");
				System.out.print("Id do emprestimo: ");
				int id = sc.nextInt();
				sc.nextLine();
				System.out.print("Id do cliente: ");
				String idC = sc.nextLine();

				ClienteService v = new ClienteService();

				List<Cliente> listaClientes = crudCliente.listarObj();
				int indiceCLiente = v.verificarId(idC);
				if (indiceCLiente != -1) { 
					System.out.print("Data de retirada: ");
					LocalDate dataS = LocalDate.parse(sc.nextLine(), dtf);

					System.out.print("Data da devolucao: ");
					LocalDate dataD = LocalDate.parse(sc.nextLine(), dtf);

					Emprestimo emprestimo = new Emprestimo(id, listaClientes.get(indiceCLiente), dataS, dataD);

					System.out.print("Quantos livros quer emprestar(so pode emprestar ate 3 livros): ");
					int[] quantidadeLivros = new int[sc.nextInt()];
					if(quantidadeLivros.length > 3) {
						do {
							System.out.println("Quantidade excedeu o limite");
							System.out.print("Informe novamente a quantidade de livros que deseja emprestar: ");
							quantidadeLivros = new int[sc.nextInt()]; 
						}while(quantidadeLivros.length > 3);
					}
					for (int j = 0; j < quantidadeLivros.length; j++) {
						System.out.print("ISBN do livro " + j + ": ");
						quantidadeLivros[j] = sc.nextInt();
					} 
 
					EmprestimoService emprestimoService = new EmprestimoService();
					emprestimoService.realizarEmprestimo(quantidadeLivros, listaClientes.get(indiceCLiente), emprestimo, listaClientes);

				}

				break;
			case 4:
				System.out.print("Informe o id so cliente para remover");
				String clienteId = sc.nextLine();
				ClienteService clienteService = new ClienteService();
				clienteService.removerCliente(clienteId);
				break;
			case 5:
				
				break;
			case 7:
				MainController mainController = new MainController();
				mainController.init();
				op = 8;
				break;
			case 8:
				System.out.println("Saindo...");
				break;
			}
		} while (op != 7);
	}

}
