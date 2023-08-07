package grafo;

import java.util.List;
import java.util.ArrayList;
public class GrafoMatriz {
	int numVerts;
	static int MaxVerts = 20;
	Vertice[] verts;
	int[][] matAd;

	public GrafoMatriz() {
		this(MaxVerts);
	}

	public GrafoMatriz(int mx) {
		matAd = new int[mx][mx];
		verts = new Vertice[mx];
		for (int i = 0; i < mx; i++)
			for (int j = 0; j < mx; j++) // Corrección: j en lugar de i
				matAd[i][j] = 0;
		numVerts = 0;
	}

	// Agregar un nuevo vertice al grafo
	public void nuevoVertice(String nom) {
		boolean esta = numVertice(nom) >= 0;
		if (!esta) {
			Vertice v = new Vertice(nom);
			v.asigVert(numVerts);
			verts[numVerts++] = v;
		}
	}

	// Obtener el índice de un vértice en el arreglo "verts" según su nombre
	int numVertice(String vs) {
		Vertice v = new Vertice(vs);
		boolean encontrado = false;
		int i = 0;
		for (; (i < numVerts) && !encontrado;) {
			encontrado = verts[i].equals(v);
			if (!encontrado)
				i++;
		}
		return (i < numVerts) ? i : -1;
	}

	// Agregar una nueva arista entre dos vértices por su nombre
	public void nuevoArco(String a, String b, int peso) throws Exception {
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    if (va < 0 || vb < 0)
	        throw new Exception("Vértice no existe");
	    matAd[va][vb] = peso; // Aquí se establece el peso del arco personalizado
	}

	// Agregar una nueva arista entre dos vértices por su índice
	public void nuevoArco(int va, int vb) throws Exception {
		if (va < 0 || vb < 0)
			throw new Exception("Vertice no existe");
		matAd[va][vb] = 1;
	}

	// Verificar si dos vértices por su nombre son adyacentes
	public boolean adyacente(String a, String b) throws Exception {
		int va, vb;
		va = numVertice(a);
		vb = numVertice(b);
		if (va < 0 || vb < 0)
			throw new Exception("Vertice no existe");
		return matAd[va][vb] == 1;
	}

	// Verificar si dos vértices por su índice son adyacentes
	public boolean adyacente(int va, int vb) throws Exception {
		if (va < 0 || vb < 0)
			throw new Exception("Vertice no existe");
		return matAd[va][vb] == 1;
	}

	// Obtener la lista de vértices conectados a un vértice por su nombre
	public List<Vertice> nodosConectados(String a) throws Exception {
		int va = numVertice(a);
		if (va < 0) {
			throw new Exception("Vertice no existe");
		}

		List<Vertice> nodosConectados = new ArrayList<>();
		for (int i = 0; i < numVerts; i++) {
			if (matAd[va][i] == 1) {
				nodosConectados.add(verts[i]);
			}
		}

		return nodosConectados;
	}

	// Recorrido en anchura desde un vértice de origen
	public int[] recorrerAnchura(String org) throws Exception {
		int w, v;
		int[] m;
		v = numVertice(org);
		if (v < 0)
			throw new Exception("Vertice origen no existe");
		ColaLista cola = new ColaLista();
		m = new int[numVerts];
		// inicializa los vértices como no marcados
		for (int i = 0; i < numVerts; i++)
			m[i] = -1;
		m[v] = 0; // vértice origen queda marcado
		cola.insertar(v);
		while (!cola.colaVacia()) {
			Integer cw = cola.quitar();
			w = cw.intValue();
			System.out.println("Vertice " + verts[w].nomVertice() + " visitado");
			// inserta en la cola los adyacentes de w no marcados
			for (int u = 0; u < numVerts; u++)
				if ((matAd[w][u] == 1) && (m[u] == -1)) {
					// se marca vertice u con número de arcos hasta el
					m[u] = m[w] + 1;
					cola.insertar(u);
				}
		}
		return m;
	}

	// Recorrido en profundidad desde un vértice de origen
	public int[] recorrerProf(String org) throws Exception {
		int v, w;
		PilaLista pila = new PilaLista();
		int[] m;
		m = new int[numVerts];
		// inicializa los vértices como no marcados
		v = numVertice(org);
		if (v < 0)
			throw new Exception("Vertice origen no existe");
		for (int i = 0; i < numVerts; i++)
			m[i] = -1;
		m[v] = 0; // vértice origen queda marcado
		pila.insertar(v);
		while (!pila.pilaVacia()) {
			Integer cw = pila.quitar();
			w = cw.intValue();
			System.out.println("Vertice " + verts[w].nomVertice() + " visitado");
			// inserta en la pila los adyacentes de w no marcados
			for (int k = 0; k < numVerts; k++) {
				if (matAd[w][k] == 1 && m[k] == -1) {
					pila.insertar(k);
					m[k] = 1; // vértice queda marcado
				}
			}
		}
		return m;
	}
	//-------------------dijikstra
	public int[] dijkstra(String origen) throws Exception {
        int vo = numVertice(origen);
        if (vo < 0) {
            throw new Exception("Vertice origen no existe");
        }

        int[] distancias = new int[numVerts];
        for (int i = 0; i < numVerts; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }

        distancias[vo] = 0;

        boolean[] visitados = new boolean[numVerts];

        for (int i = 0; i < numVerts - 1; i++) {
            int u = minDistancia(distancias, visitados);
            visitados[u] = true;

            for (int v = 0; v < numVerts; v++) {
                if (!visitados[v] && matAd[u][v] != 0 && distancias[u] != Integer.MAX_VALUE
                        && distancias[u] + matAd[u][v] < distancias[v]) {
                    distancias[v] = distancias[u] + matAd[u][v];
                }
            }
        }

        return distancias;
    }

    private int minDistancia(int[] distancias, boolean[] visitados) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVerts; v++) {
            if (!visitados[v] && distancias[v] <= min) {
                min = distancias[v];
                minIndex = v;
            }
        }

        return minIndex;
    }
}
