import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CSVReader {

	public CSVReader() {
	}

	/*
	*
	*  LA COMPLEJIDAD COMPUTACIONAL DE ESTE METODO ES O(T), DONDE T ES LA CANTIDAD DE TAREAS
	*
	* */
	public HashMap<String,Tarea> readTasks(String taskPath) {
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así

		/* O(T) donde "T" es la cantidad de tareas*/
		ArrayList<String[]> lines = this.readContent(taskPath);

		/*
		* INICIALIZAMOS EL MAPA DEL TAMAÑO DE LINES X 2 POR QUE SI NO VA A HACER UN CRECIMIENTO EL MAPA
		*  Y CREEMOS QUE LO PODRIAMOS EVITAR HACIENDO ESO
		* */
		HashMap<String, Tarea> resultante = new HashMap<>(lines.size()*2);

		/* O(T) donde "T" es la cantidad de tareas*/
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			Tarea actual = new Tarea(id,nombre,tiempo,critica,prioridad);
			resultante.put(actual.getId_tarea(),actual);
		}
		return resultante;
	}

	//NO LO UTILIZAMOS EN LA PARTE UNO
public LinkedList<Procesador> readProcessors(String processorPath) {
		LinkedList<Procesador> resultante = new LinkedList<>();

		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así

		/* o(P) donde "P" es la cantidad de procesadores */
		ArrayList<String[]> lines = this.readContent(processorPath);

		/* o(P) Donde "P" es la cantidad de procesadores */
		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			// Aca instanciar lo que necesiten en base a los datos leidos
			Procesador actual = new Procesador(id,codigo,refrigerado,anio);
			resultante.add(actual);
		}
		return resultante;
	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
}
