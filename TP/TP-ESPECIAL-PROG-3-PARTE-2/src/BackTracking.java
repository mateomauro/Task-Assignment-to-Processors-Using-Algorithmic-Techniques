import java.util.ArrayList;
import java.util.LinkedList;

public class BackTracking {
    private ArrayList<Tarea> listaTareas;
    private ArrayList<Procesador> listaProcesadores;
    private Solucion mejorSolucion;
    private int cantEstados;
    private int cantMaxCriticas;

    public BackTracking(){
        CSVReader lector = new CSVReader();
        this.listaTareas = lector.readTasks("./src/datasets/Tareas.csv");
        this.listaProcesadores = lector.readProcessors("./src/datasets/Procesadores.csv");
        this.mejorSolucion = new Solucion();
        this.cantEstados = 0;
        this.cantMaxCriticas = 2;
    }

    /*
     * EN NUESTRO METODO UTILIZANDO LA TECNICA BACKTRACKING,BUSCAMOS ASIGNAR CADA UNA DE NUESTRAS TAREAS A TODOS LOS  PROCESADORES, ALTERNANDO ENTRE
     * CADA UNO DE ÉSTOS TODAS LAS POSIBLES COMBINACIONES DE TAREAS EXITENTES DE A CUERDO A NUESTRO DOMINIO DE TAREAS, UNA VEZ REALIZADA ESTA ASIGNACIÓN
     * SE EVALÚA POR CADA "RAMA" SI LA SOLUCION PARCIAL ENCONTRADA ES MEJOR QUE LA ENCONTRADA HASTA EL MOMENTO (SU TEIMPO DE EJECUCION MAYOR ES EL MÍNIMO POSIBLE),
     * DE SER MEJOR LA GUARDAMOS EN NUESTRO ATRIBUTO(mejorSolucion), Y EN CADA ITERACION SE EVALÚA SI NO SE ENCONTRO UNA MEJOR OPCION.
     * UNA VEZ GENERADAS TODAS LAS POSIBLES SOLUCIONES, SE RETORNA LA MEJOR SOLUCION ENCONTRADA CON RESPECTO A LA CONFIGURACION DADA DE PROCESADORES Y TAREAS.
     * TAMBIEN SE GENERA UNA CANTIDAD DE ESTADOS COMO MÉTRICA,ÉSTA MISMA REFIERE A LA CANTIDAD DE VECES QUE SE HA REALIZADO UN LLAMADO AL MÉTODO RECURSIVO.
     * */
    public Solucion asignarTareas(int tMax){
        Solucion solucionActual = new Solucion(this.listaProcesadores);
        // SI LA LISTA DE TAREAS ESTA VACIA RETORNAMOS UNA SOLUCION VACIA
        if(this.listaTareas.isEmpty()){
            return new Solucion();
        } else {
            this.asignarTareas(solucionActual,tMax,0);
        }

        mejorSolucion.setCantEstados(this.cantEstados);
        return mejorSolucion;
    }

    private void asignarTareas(Solucion solucionActual,int tMax, int indice) {
        cantEstados++;
        //CONDICION DE UNA SOLUCION ENCONTRADA
        if (this.listaTareas.size() == solucionActual.getSizeVisitados()) {
            //VERIFICAMOS SI LA SOLUCION QUE ENCONTRAMOS ES MEJOR QUE LA MEJOR SOLUCION, O EN SU DEFECTO SI NO SE SETEO NINGUNA MEJOR SOLUCION
            if (mejorSolucion.estoyVacio() || solucionActual.getMayorTiempo() < mejorSolucion.getMayorTiempo()) {
                //DE SER ASÍ SE REALIZA UNA COPIA DE LA SOLUCION ACTUAL PARA NO PERDER LOS DATOS ALMACENADOS HASTA EL MOMENTO("solucionActual" MODIFICA SUS VALORES EN TODAS LAS ITERACIONES)
                mejorSolucion = solucionActual.copiar();
            }
        }else{
            for (Procesador procesadorActual : this.listaProcesadores) {
                Tarea tareaActual = this.listaTareas.get(indice);

                //CONDICION DE PROCESADOR NO REFRIGERADO CON TIEMPO MENOR A X
                if (!procesadorActual.isEsta_refrigerado() && ((procesadorActual.gettEjecucion() + tareaActual.getTiempo_ejecucion()) > tMax)) {
                    tareaActual = null;
                }

                //CONDICION DE PROCESADOR CON MENOS DE 2 CRITICAS
                if (tareaActual != null && (tareaActual.esCritica() && procesadorActual.getCantCriticas() == cantMaxCriticas)) {
                    tareaActual = null;
                }

                if (tareaActual != null) {
                    //AGREGO LA TAREA AL PROCESADOR(PROPIO DE BACKTRACKING)
                    procesadorActual.agregarTarea(tareaActual);
                    solucionActual.agregarVisitado(tareaActual);
                    indice++;
                    //SOLAMENTE SEGUIMOS BUSCANDO SI EL MAYOR TIEMPO DE LA SOLUCION ACTUAL ES MENOR QUE EL MAYOR TIEMPO DE LA MEJOR SOLUCION,O QUE NO SE HAYA SETEADO UNA MEJOR SOLUCION(CONDICIÓN DE PODA)
                    if (solucionActual.getMayorTiempo() < this.mejorSolucion.getMayorTiempo() || this.mejorSolucion.getMayorTiempo() == 0) {
                        this.asignarTareas(solucionActual, tMax, indice);
                    }
                    //QUITO LA TAREA DEL PROCESADOR(PROPIO DE BACKTRACKING)
                    indice--;
                    solucionActual.quitarVisitado(tareaActual);
                    procesadorActual.eliminarTarea(tareaActual);
                }
            }
        }
    }
}