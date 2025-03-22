import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import crud.CRUDLivros;
import model.entities.Cliente;
import model.entities.Emprestimo;
import model.entities.Livro;

public class Program {

	public static void main(String args[]) {
		MainController mc = new MainController();
		mc.init();
		/*List<Cliente> clientes = new ArrayList<>();
		CRUDLivros crud = new CRUDLivros();

		// Criar livros
	
		
		
		// Criar clientes
		/*Cliente cliente1 = new Cliente("Paulo", "129389", "872910923");
		Cliente cliente2 = new Cliente("Kira", "019208", "822910923");
		Cliente cliente3 = new Cliente("Lira", "018602", "820910923");
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);

		System.out.println("LISTAR LIVROS");
		List<Livro> livross = crud.listarLivros();
		try {
			crud.apagarLivros(112);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		for (Livro lista : livross) {
			System.out.println(lista);
		}
		
		
		System.out.println();
		System.out.println("LISTAR LIVROS");
		List<Livro> livro = crud.listarLivros();
		for (Livro lista : livro) {
			System.out.println(lista);
		}
*/
	}

}
