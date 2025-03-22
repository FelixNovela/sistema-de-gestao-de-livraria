package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import crud.Crud;
import model.entities.Livro;
import model.service.LivrosService;
import view.LivrosView;

public class LivrosController {
	private LivrosView livrosView;
	private LivrosService validarLivro = new LivrosService();;
	private MainController mainController;
	private Crud crud;
	private static Scanner sc;
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public void gerenciarLivros(Scanner sc) {
		this.sc = sc;
		int op;
		
		do {
			this.crud = new Crud("livros.dat");
			List<Livro> lista = crud.listarObj();
			
			this.livrosView = new LivrosView();
			livrosView.Menu();
			op = sc.nextInt();
			switch(op) { 
			case 1:
				
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				System.out.println("ADICIONAR LIVRO");
				System.out.println();
				System.out.print("ISBN: ");
				int isbn = sc.nextInt();
				
				int indice = validarLivro.validarIsbn(isbn, lista);
				
				if(indice != -1) {
					
					System.out.println("Livro com ISBN ja existente, quer atualizar a quantidade no stock(s/n)?");
					char resp = sc.next().charAt(0);
					if(resp == 's') {
						System.out.println("Quantidade: ");
						int quantidade = sc.nextInt();
						lista.get(indice).aumentarEstoque(quantidade);
						crud.atualizar(lista, indice);
						break;
					}else {
						livrosView.Menu();
						break;
					}
					
				}
				
				sc.nextLine();
				
				System.out.print("Titulo: ");
				String titulo = sc.nextLine();
				
				System.out.print("AUtor: ");
				String autor = sc.nextLine();
				
				System.out.print("Data de publicacao: ");
				LocalDate dateanoPublicacao = LocalDate.parse(sc.nextLine(), df);
				
				System.out.println("Quantidade: ");
				int quantidade = sc.nextInt();
				
				if(validarLivro.validarAdicaoLivro(new Livro(isbn,titulo,autor,dateanoPublicacao,quantidade), this.crud.listarObj())) {
					this.crud.novoObjecto(new Livro(isbn,titulo,autor,dateanoPublicacao,quantidade));
				}
				
				
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				break;
				
			case 2:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println("LISTA DE LIVROS");
				System.out.println();
				this.livrosView.listarLivros(this.crud.listarObj());
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
				
			case 3:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println("ATUALIZACAO DA QUANTIDADE NO STOCK");
				System.out.println();
				System.out.print("ID do livro: ");
				int idAt = sc.nextInt();
				System.out.print("Quantidade: ");
				int quantidadeAt = sc.nextInt();
				
				if(validarLivro.validarIsbn(idAt, crud.listarObj()) != -1) {
					lista = crud.listarObj();
					lista.get(validarLivro.validarIsbn(idAt, crud.listarObj())).aumentarEstoque(quantidadeAt);
					crud.atualizar(lista, validarLivro.validarIsbn(idAt, crud.listarObj()));
				}
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
				
			case 4:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				System.out.print("ID do livro: ");
				int idRm = sc.nextInt();
				/*indice = validarLivro.validarId(idRm, crud.listarObj());
				if(indice != -1) {
					lista = crud.listarObj();
					lista.get(indice).setAtivo(false);
					crud.atualizar(lista, indice);
					
				}*/
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
			case 5:
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				this.mainController = new MainController();
				this.mainController.init();
				op = 6;
				System.out.println();
				System.out.println("_________________________________________________________________________________________________________________________________________");
				System.out.println();
				break;
			case 6:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opcao invalida");
				break;
			}
		}while(op != 6);
	}
}
