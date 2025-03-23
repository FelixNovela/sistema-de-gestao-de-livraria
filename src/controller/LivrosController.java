package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import crud.Crud;
import model.entities.Livro;
import model.service.LivrosService;
import view.LivrosView;

public class LivrosController {
	private LivrosView livrosView;
	private LivrosService livroService = new LivrosService();;
	private MainController mainController;
	private static Scanner sc;
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public void gerenciarLivros(Scanner sc) {
		this.sc = sc;
		int op;
		
		do {
			
			
			LivrosService livrosService = new LivrosService();
			
			
			this.livrosView = new LivrosView();
			livrosView.Menu();
			op = sc.nextInt();
			sc.nextLine();
			switch(op) { 
			case 1:
				
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				System.out.println("ADICIONAR LIVRO");
				System.out.println();
				System.out.print("ISBN: ");
				String isbn = sc.nextLine();
				
				int indice = livroService.verificarIsbn(isbn);
				
				if(indice != -1) {
					
					System.out.println("Livro com ISBN ja existente, quer atualizar a quantidade no stock(s/n)?");
					char resp = sc.next().charAt(0);
					if(resp == 's') {
						System.out.println("Quantidade: ");
						int quantidade = sc.nextInt();
						livrosService.atulizarQuantidadeLivros(isbn, quantidade);
						break;
					}else {
						livrosView.Menu();
						break;
					}
					
				}
				
				
				
				System.out.print("Titulo: ");
				String titulo = sc.nextLine();
				
				System.out.print("AUtor: "); 
				String autor = sc.nextLine();
				
				System.out.print("Data de publicacao (dd/MM/yyyy): ");
				LocalDate dateanoPublicacao = LocalDate.parse(sc.nextLine(), df);
				
				 
				System.out.println("Quantidade: ");
				int quantidade = sc.nextInt();
				
				livroService.adicionarLivro(new Livro(isbn,titulo,autor,dateanoPublicacao,quantidade));
				
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				break;
				
			case 2: 
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println("LISTA DE LIVROS");
				System.out.println();
				livrosView.listarLivros(livrosService.listarLivros());
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
				
			case 3:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println("ATUALIZACAO DA QUANTIDADE NO STOCK");
				System.out.println();
				
				System.out.print("ISBN do livro: ");
				isbn = sc.nextLine();
				
				System.out.print("Quantidade: ");
				quantidade = sc.nextInt();
				
				livroService.atulizarQuantidadeLivros(isbn, quantidade); 
					
				
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
				
			case 4:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				
				System.out.print("ISBN do livro: ");
				isbn = sc.nextLine();
				livrosService.removerLivro(isbn); 
				
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
			case 5:
				System.out.println("Pesquisar");
				System.out.print("ISBN: ");
				isbn = sc.nextLine();
				System.out.println(livrosService.pesquisarLivro(isbn));
				break;
			case 6:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				this.mainController = new MainController();
				this.mainController.init();
				 
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
			case 7:
				System.out.println("Saindo...");
				op = 6;
				break;
			default:
				System.out.println("Opcao invalida");
				break;
			}
		}while(op != 6);
	}
}
