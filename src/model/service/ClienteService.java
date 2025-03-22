package model.service;

import java.util.List;

import crud.Crud;
import model.entities.Cliente;
import model.entities.enums.StatusEmprestimo;

public class ClienteService {
	private static Crud crud = new Crud("clientes.dat");
	private List<Cliente> listaClientes = crud.listarObj();
	
	public void removerCliente(String id) {
		int indiceCliente = verificarId(id);
		
		if(indiceCliente != -1) {
			System.out.println("reree");
			for(int i = 0; i < listaClientes.get(indiceCliente).getEmprestimosAtivos().size(); i++) {
				//StatusEmprestimo statusEmprestimo = listaClientes.get(indiceCliente).getEmprestimosAtivos().get(i).getStatusEmprestimo();
				if(listaClientes.get(indiceCliente).getEmprestimosAtivos().get(i).getStatusEmprestimo().equals(StatusEmprestimo.ATIVO)) {
					System.out.println("Nao pode remover "+listaClientes.get(indiceCliente).getNome()+" tem emprestimos ativos");
					break;
				}
				if(listaClientes.get(indiceCliente).getEmprestimosAtivos().get(i).getStatusEmprestimo().equals(StatusEmprestimo.ATRASADO)) {
					System.out.println("Nao pode remover "+listaClientes.get(indiceCliente)+" tem emprestimos atrasados");
					break;
				}
			}
		} 
	}
	public int verificarId(String id) {
		for(int i = 0; i < listaClientes.size(); i++) {
			if(id.equalsIgnoreCase(listaClientes.get(i).getIsbn())){
				return i; 
			}  
			if(!(id.equalsIgnoreCase(listaClientes.get(i).getIsbn())) && i == listaClientes.size() - 1){
				return -1;
			}  
		}   
		return -1;   
	}
}
