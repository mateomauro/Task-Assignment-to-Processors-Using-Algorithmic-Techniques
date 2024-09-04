import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String args[]) {

		BackTracking back = new BackTracking();
		Solucion solucionBack = back.asignarTareas(60);


		Greedy greedy = new Greedy();
		Solucion solucionGreedy = greedy.asignarTareas(80);



		//MOSTRAR SOLUCION BACKTRACKING
		System.out.println("Solucion Backtracking:");
		imprimirSolucion(solucionBack);


		//MOSTRAR SOLUCION GREEDY
		System.out.println("Solucion Greedy:");
		imprimirSolucion(solucionGreedy);
	}

	public static void imprimirSolucion (Solucion solucion){
		if(!solucion.estoyVacio()) {
			Iterator<Procesador> procesadores = solucion.getProcesadores();
			System.out.println("La mejor solucion es: ");
			while (procesadores.hasNext()) {
				Procesador pro = procesadores.next();
				System.out.println(pro);
			}
			System.out.println("El tiempo de ejecucion mayor es: " + solucion.getMayorTiempo());
		}else{
			System.out.println("No se encontro una solucion a lo planteado");
		}
		System.out.println("Se encontraron en total: " + solucion.getCantEstados()+ " estados.");
	}
}
