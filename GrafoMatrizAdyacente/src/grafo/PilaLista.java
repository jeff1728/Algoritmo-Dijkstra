package grafo;

public class PilaLista {
	private Nodo cima;
	public PilaLista() {
		cima = null;
	}
	public boolean pilaVacia() {
		return cima == null;
	}
	public void insertar(Integer elemento) {
		Nodo nuevo = new Nodo(elemento);
		nuevo.siguiente = cima;
		cima = nuevo;
	}
	public Integer quitar() {
		if (pilaVacia()) {
			return null;
		}
		Integer elemento = cima.dato;
		cima = cima.siguiente;
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
