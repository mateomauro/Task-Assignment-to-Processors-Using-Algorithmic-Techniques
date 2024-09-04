//import tpe.utils.CSVReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private LinkedList<Procesador> procesadores;
	private HashMap<String, Tarea> colaTareas;
	private LinkedList<Tarea> tareasCriticas;
	private LinkedList<Tarea> tareasNoCriticas;

	/*
     *	LA COMPLEJIDAD ES O(T*2) DONDE "T" ES LA CANTIDAD DE TAREAS Y FINALMENTE QUEDARIA O(T)
     */
	public Servicios(String pathProcesadores, String pathTareas) {
		CSVReader reader = new CSVReader();
		//NO LOS INSTANCIAMOS POR QUE NO LO UTILIZAMOS
		//this.procesadores = reader.readProcessors(pathProcesadores);
		this.colaTareas = reader.readTasks(pathTareas);
		// Precarga de listas de tareas críticas y no críticas
		tareasCriticas = new LinkedList<>();
		tareasNoCriticas = new LinkedList<>();
		// RECORRE LAS TAREAS Y LAS ASIGNA A UNA LISTA DE CRITICAS O EN SU DEFECTO, NO CRITICAS
		// LA COMPLEJIDAD ES O(T)
		for (Tarea tarea : colaTareas.values()) {
			if (tarea.esCritica()) {
				tareasCriticas.add(tarea);
			} else {
				tareasNoCriticas.add(tarea);
			}
		}
	}

	/* LA COMPLEJIDAD DE AGREGAR ES O(1) PORQUE SIEMPRE AGREGA AL FINAL*/
	public void agregarProcesador(Procesador procesador){
		this.procesadores.add(procesador);
	}


	/*
     *	LA COMPLEJIDAD DEL SERVICIO 1 ES O(1)
     */
	public Tarea servicio1(String ID) {
		return colaTareas.get(ID);
	}
    
    /*
     	LA COMPLEJIDAD DEL SERVICIO 2 ES O(1) 
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica){
			return this.tareasCriticas;
		}else {
			return this.tareasNoCriticas;
		}
	}


    /*
     *	LA COMPLEJIDAD DEL SERVICIO 3 ES O(T) DONDE "T" ES LA CANTIDAD DE
     *	TAREAS, LO DEJAMOS DE ESTA FORMA YA QUE SIEMPRE EN EL PEOR DE LOS CASOS
     *	SE RECORREN SIEMPRE TODAS LAS TAREAS
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> tareasEntre = new LinkedList<>();
		for (Tarea tarea : this.colaTareas.values()) {
			int nivel = tarea.getNivel_prioridad();
			if (nivel >= prioridadInferior && nivel <= prioridadSuperior) {
				tareasEntre.add(tarea);
			}
		}
		return tareasEntre;
	}
	
}
