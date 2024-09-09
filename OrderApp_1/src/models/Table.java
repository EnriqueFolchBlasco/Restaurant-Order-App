package models;

import java.util.ArrayList;

public class Table {
	
	int id;
	ArrayList<Product> listaProductos;
	int total;
	
	public Table(int id, ArrayList<Product> listaTipos, int total) {
		super();
		this.id = id;
		this.listaProductos = new ArrayList<Product>();
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Product> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Product> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Table " + id;
	}
	
	

}
