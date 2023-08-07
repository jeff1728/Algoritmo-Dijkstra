package grafo;

public class Arco {
	private int destino;
	private int peso;

	public Arco(int destino, int peso) {
		this.destino = destino;
		this.peso = peso;
	}

	public int getDestino() {
		return destino;
	}

	public int getPeso() {
		return peso;
	}
}
