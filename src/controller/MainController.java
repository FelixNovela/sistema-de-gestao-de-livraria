package controller;

import java.util.Scanner;

import model.entities.Livro;
import view.LivrosView;
import view.MainView;

public class MainController {
	private Livro livro;
	private LivrosView livrosView;
	private MainView mainView;
	private LivrosController livrosController;
	private ClienteController clienteController;
	private static Scanner sc =new Scanner(System.in);

	public void init() {
	
		mainView = new MainView(); 
		mainView.Menu();

			
			int op = sc.nextInt(); 
			switch(op) {  
			case 1:
				System.out.println();
				System.out.println();
				this.livrosController = new LivrosController();
				this.livrosController.gerenciarLivros(sc);
				break; 
			case 2:
				System.out.println();
				System.out.println();
				this.clienteController = new ClienteController();
				clienteController.gerenciarCliente();
				break; 
			case 6:
				System.out.println("Saindo...");
				break;
			}
		
		
	}
	
}