import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Greedy {
    private ArrayList<Tarea> listaTareas;
    private ArrayList<Procesador> listaProcesadores;
    private int cantMaxCriticas;
    private int cantEstados;

    //OBTENEMOS LAS TAREAS Y PROCESADORES INSTANCIANDO CSVReader Y LAS GUARDAMOS
    public Greedy(){
        CSVReader lector = new CSVReader();
        this.listaTareas = lector.readTasks("./src/datasets/Tareas.csv");
        this.listaProcesadores = lector.readProcessors("./src/datasets/Procesadores.csv");
        this.cantMaxCriticas = 2;
        this.cantEstados = 0;
    }

    /*
    * EN NUESTRO ALGORITMO GREEDY, EN UN PRINCIPIO ORDENAMOS LAS TAREAS DE MAYOR A MENOR,SEGUN EL TIEMPO DE EJECUCION
    * LUEGO ASIGNAMOS SECUENCIALMENTE UNA TAREA A CADA PROCESADOR (RESPETANDO LAS RESTRICCIONES DE LA CONSIGNA)
    * CONSECUENTEMENTE VA ASIGNANDO UNA TAREA POR PROCESADOR, RECORRIENDO LA LISTA DE PROCESADORES AL DERECHO Y AL REVEZ.
    * QUEDANDO COMO RESULTADO UNA DISTRIBUCION SIMILAR A LA SIGUIENTE:
    * TAREAS = [T1(TiempoEj:50),T2(TiempoEj:40),T3(TiempoEj:30),T4(TiempoEj:20),T5(TiempoEj:10),T6(TiempoEj:5)]
    * PROCESADORES = [P1=(T1,T6),P2=(T2,T5),P3=(T3,T4)]
    * BUSCANDO COMO OBJETIVO LA DISTRIBUCION MAS PAREJA DE TIEMPO DE EJECUCION TOTAL(TIEMPO QUE TARDA EN EJECUTAR TODAS LAS TAREAS) POR PROCESADOR,
    * RESULTANDO:
    * PROCESADORES = [P1=TiempoEj:55,P2=TiempoEj:50,P3=TiempoEj:50]
     * */
    public Solucion asignarTareas(int tMax){
        // SI LA LISTA DE TAREAS ESTA VACIA, RETORNAMOS UNA SOLUCION VACIA
        if(this.listaTareas.isEmpty()){
            return new Solucion();
        }
        //ORDENAMOS LAS TAREAS DE MAYOR A MENOR
        Collections.sort(this.listaTareas);
        Solucion solucion = new Solucion(this.listaProcesadores);
        boolean sentido = true;
        int numeroProcesador = 0;
        int maxProcesadores = listaProcesadores.size();
        int intentosAsignacion = 0;
        //MIENTRAS LA LISTA DE TAREAS NO ESTE VACIA
        while(!this.listaTareas.isEmpty()) {
            /*LLEVAMOS UN CONTADOR DE INTENTOS DE ASIGNACION QUE NOS INFORMA CUANTAS VECES SE INTENTO ASIGNAR UNA TAREA SIN EXITO (ESTA VARIABLE, CORRESPONDE A UNA MISMA TAREA
             *, UNA VEZ ASIGNADA ESTA TAREA, SE SETEA EN 0), POR CADA ITERACION SE CONSULTA EL VALOR DE ESA VARIABLE, SI SU VALOR ES MAYOR O IGUAL AL DOBLE DE LOS PROCESADORES
             * QUIERE DECIR QUE SE INTENTO ASIGNAR A TODOS LOS PROCESADORES, Y NO HUBO EXITO, POR LO QUE NO SE ENCONTRO SOLUCION PARA ESTA CONFIGURACION DE TAREAS Y PROCESADORES
             */
            if(intentosAsignacion < (maxProcesadores*2)){
                Procesador pActual = this.listaProcesadores.get(numeroProcesador);
                Tarea asignable = this.listaTareas.getFirst();

                //RESTRICCIONES DEL ENUNCIADO-->NO MAS DE DOS CRITICAS POR PROCESADOR Y LOS PROCESADORES NO REFRIGERADOS, NO PUEDEN EJECUTAR MAS DE X TIEMPO EN TAREAS
                if ((pActual.isEsta_refrigerado()||(pActual.gettEjecucion()+asignable.getTiempo_ejecucion()<=tMax)) && ((pActual.getCantCriticas()<this.cantMaxCriticas) || !asignable.esCritica())) {
                    pActual.agregarTarea(this.listaTareas.removeFirst());
                    intentosAsignacion = 0;
                    cantEstados++;
                } else {
                    intentosAsignacion++;
                    cantEstados++;
                }

                //EL SENTIDO INDICA SI NUESTRA COLECCION DE ARREGLOS SE RECORRE AL DERECHO O AL REVEZ (TRUE->DERECHO,FALSE->REVEZ)
                if (sentido) {
                    if (numeroProcesador == maxProcesadores - 1) {
                        sentido = false;
                    } else {
                        numeroProcesador++;
                    }
                } else {
                    if (numeroProcesador > 0) {
                        numeroProcesador--;
                    } else {
                        sentido = true;
                    }

                }

            }else{
                return new Solucion();
            }
        }

        solucion.setCantEstados(cantEstados);
        return solucion;
    }
}