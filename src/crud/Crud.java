package crud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Crud <T>{
	private String nomeFicheiro;
	public Crud(String nomeFicheiro) {
		this.nomeFicheiro = nomeFicheiro;
	}
	
	public void novoObjecto(T object) {
		List<T> objectos = listarObj();
		objectos.add(object);
		salvarObj(objectos);
	}
	
	public List<T> listarObj(){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))){
			return (List<T>) ois.readObject();
		}catch(FileNotFoundException e) {
			return new ArrayList<>();
		}catch(IOException | ClassNotFoundException e) { 
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void atualizar(List<T> objs, int indice) {
		List<T> lista = objs;
		lista.set(indice, lista.get(indice));
		salvarObj(lista);
		
	}
	
	public void removerObj(List<T> objs, int indice) {
		List<T> lista = objs;
		lista.set(indice, lista.get(indice));
		salvarObj(lista);
	}
	
	
	public void salvarObj(List<T> objectos) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))){
			oos.writeObject(objectos);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
