package grafo;

public class ColaLista {
	private Nodo frente;
	private Nodo fin;
	public ColaLista() {
		frente = fin = null;}
	public boolean colaVacia() {
		return frente == null;
	}
	public void insertar(Integer elemento) {
		Nodo nuevo = new Nodo(elemento);
		if (colaVacia()) {
			frente = fin = nuevo;
		} else {
			fin.siguiente = nuevo;
			fin = nuevo;
		}
	}
	public Integer quitar() {
		if (colaVacia()) {
			return null;
		}
		Integer elemento = frente.dato;
		frente = frente.siguiente;
		return elemento;
	}
	private class Nodo {
		Integer dato;
		Nodo siguiente;
		public Nodo(Integer dato) {
			this.dato = dato;
			siguiente = null;
		}
	}
}
