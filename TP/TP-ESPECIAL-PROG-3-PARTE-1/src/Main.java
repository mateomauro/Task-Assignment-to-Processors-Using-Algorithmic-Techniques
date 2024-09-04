import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");

		// SERVICIO 1
		Tarea tarea = servicios.servicio1("T1");
		if(tarea!=null) {
			System.out.println(tarea.getNombre_tarea());
		}else{
			System.out.println("No existe una tarea con ese ID");
		}

		System.out.println("------------------------------------");

		// SERVICIO 2
		List<Tarea> listaCriticos = servicios.servicio2(true);
		if(listaCriticos.isEmpty()){
			System.out.println("No se encontraron resultados");
		}else{
			for (Tarea actualTarea: listaCriticos) {
			if (actualTarea.esCritica()) {
				System.out.println(actualTarea.getNombre_tarea() + " es critica: " + actualTarea.esCritica());
			}else{
				System.out.println(actualTarea.getNombre_tarea() + " no es critica: " + actualTarea.esCritica());
			}

			}
		}

		System.out.println("------------------------------------");

		// SERVICIO 3
		List<Tarea> listaEntre = servicios.servicio3(50, 100);
		if(listaEntre.isEmpty()){
			System.out.println("No se encontraron resultados para esos valores de prioridad");
		}else{
			for (Tarea actualTarea: listaEntre){
				System.out.println(actualTarea.getNombre_tarea() + " su prioridad es: " + actualTarea.getNivel_prioridad());
			}
		}

	}
}
