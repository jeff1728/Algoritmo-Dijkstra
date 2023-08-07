package grafo;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		int opcion = 0;
		GrafoMatriz grafo = new GrafoMatriz();
		do {
			System.out.println("============================================");
			System.out.println("  			MENU DE OPCIONES  ");
			System.out.println("============================================");
			System.out.println("1. Insertar vertices");
			System.out.println("2. Conectar vertices");
			System.out.println("3. Verificar si dos vertices son adyacentes");
			System.out.println("4. Mostrar nodos conectados a un vertice");
			System.out.println("5. Recorrido en anchura");
			System.out.println("6. Recorrido en profundidad");
			System.out.println("7. Algoritmo Dijkstra");
			System.out.println("8. Salir");
			System.out.println("============================================");
			System.out.print("Ingrese una opcion: ");
			opcion = leer.nextInt();
			switch (opcion) {
			case 1:
				ingresarVertices(grafo,leer);
				break;
			case 2:
				conectarVertices(grafo, leer);
				break;
			case 3:
				verificarAdyacencia(grafo, leer);
				break;
			case 4:
				mostrarNodosConectados(grafo, leer);
				break;
			case 5:
				recorridoAnchura(grafo, leer);
				break;
			case 6:
				recorridoProfundidad(grafo, leer);
				break;
			case 7:
				recorrerDijkstra(grafo, leer);
				
				break;
			case 8:
				System.out.println("Salio con exito");
				break;
			default:
				System.out.println("ERROR INTENTE OTRA VEZ");
			}

		} while (opcion != 8);
	}

	//---------------------
	public static void ingresarVertices(GrafoMatriz grafo, Scanner leer) {
		int numVertices;
		do {
			System.out.print("Ingrese numero de vertices (max. 20): ");
			numVertices = leer.nextInt();
			if (numVertices <= 0 || numVertices > 20) {
				System.out.println("Cantidad invalida. Debe ser > 0 y <= 20");
			}
		} while (numVertices <= 0 || numVertices > 20);
		leer.nextLine(); // Limpiar el buffer de entrada

		for (int i = 0; i < numVertices; i++) {
			System.out.print("Ingrese el nombre del vertice (nodo) " + (i + 1) + ": ");
			String nombreVertice = leer.nextLine();
			grafo.nuevoVertice(nombreVertice);
		}
		System.out.println("Vertices ingresados correctament");
	}

	//---------------------
	public static void conectarVertices(GrafoMatriz grafo, Scanner leer) {
		System.out.print("Ingrese el nombre del primer vertice a conectar: ");
		String a = leer.next();
		System.out.print("Ingrese el nombre del segundo vertice a conectar: ");
		String b = leer.next();

		 System.out.print("Ingrese el peso del arco: ");
		    int peso = leer.nextInt();

		    try {
		        grafo.nuevoArco(a, b, peso);
		        System.out.println("Vertices conectados correctamente con un peso de " + peso);
		    } catch (Exception e) {
		        System.out.println("Error: " + e.getMessage());
		    }
	}

	//---------------------
	public static void verificarAdyacencia(GrafoMatriz grafo, Scanner leer) {
		System.out.print("Ingrese primer vertice: ");
		String a = leer.next();
		System.out.print("Ingrese segundo vertice: ");
		String b = leer.next();

		try {
			boolean adyacentes = grafo.adyacente(a, b);
			System.out.println("Los vertices " + a + " y " + b + " " + (adyacentes ? "son adyacentes" : "no son adyacentes"));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	//---------------------
	public static void mostrarNodosConectados(GrafoMatriz grafo, Scanner leer) {
		System.out.print("Ingrese el nombre del vertice para buscar sus conexiones: ");
		String a = leer.next();

		try {
			List<Vertice> nodosConectados = grafo.nodosConectados(a);
			System.out.println("Vertices conectados a " + a + ":");
			for (Vertice v : nodosConectados) {
				System.out.println(v.nomVertice());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	//---------------------------
	public static void recorridoAnchura(GrafoMatriz grafo, Scanner leer) {
		System.out.print("Ingrese el vertice origen para el recorrido en anchura: ");
		String org = leer.next();

		try {
			int[] m = grafo.recorrerAnchura(org);
			System.out.println("Distancias desde el vertice " + org + " hasta los demas vertices:");
			for (int i = 0; i < m.length; i++) {
				System.out.println(grafo.verts[i].nomVertice() + ": " + m[i]);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	//--------------------
	public static void recorridoProfundidad(GrafoMatriz grafo, Scanner leer) {
		System.out.print("Ingrese el vertice origen para el recorrido en profundidad: ");
		String org = leer.next();

		try {
			int[] m = grafo.recorrerProf(org);
			System.out.println("Vertices visitados en el recorrido en profundidad desde el vertice " + org + ":");
			for (int i = 0; i < m.length; i++) {
				if (m[i] != -1) {
					System.out.println(grafo.verts[i].nomVertice());
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static void recorrerDijkstra(GrafoMatriz grafo, Scanner leer) {
        System.out.print("Ingrese el vertice origen para el recorrido Dijkstra: ");
        String org = leer.next();

        try {
            int[] distancias = grafo.dijkstra(org);
            System.out.println("Distancias minimas desde el vertice " + org + " hasta los demas vertices:");
            for (int i = 0; i < distancias.length; i++) {
                System.out.println(grafo.verts[i].nomVertice() + ": " + distancias[i]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}