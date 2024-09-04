import java.util.ArrayList;
import java.util.HashMap;

public class Procesador implements Comparable<Procesador>{
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private int año_funcionamiento;
    private ArrayList<Tarea> misTareas;
    private int cantCriticas;
    private int tEjecucion;

    public Procesador(String id_procesador, String codigo_procesador, boolean esta_refrigerado, int año_funcionamiento) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.año_funcionamiento = año_funcionamiento;
        this.misTareas = new ArrayList<>();
        this.cantCriticas = 0;
        this.tEjecucion = 0;
    }

    public String getId_procesador() {
        return id_procesador;
    }

    public String getCodigo_procesador() {
        return codigo_procesador;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public int getAño_funcionamiento() {
        return año_funcionamiento;
    }

    public void agregarTarea(Tarea tarea){
        this.misTareas.add(tarea);
        tEjecucion += tarea.getTiempo_ejecucion();
        if(tarea.esCritica()){
            cantCriticas++;
        }
    }

    public void eliminarTarea(Tarea tarea){
        this.misTareas.remove(tarea);
        tEjecucion -= tarea.getTiempo_ejecucion();
        if(tarea.esCritica()){
            cantCriticas--;
        }
    }

    public Procesador copiar() {
        Procesador copia = new Procesador(this.id_procesador, this.codigo_procesador, this.esta_refrigerado, this.año_funcionamiento);
        for (Tarea t : this.misTareas) {
            copia.agregarTarea(t.copiar());
        }
        return copia;
    }

    public String toString (){
        return this.id_procesador +" ejecuto las tareas : "+this.misTareas+ " y las ejecuto en: " +  this.tEjecucion;
    }

    public int getCantCriticas(){
        return this.cantCriticas;
    }

    public int gettEjecucion(){
        return tEjecucion;
    }

    @Override
    public int compareTo(Procesador otro) {
        return this.id_procesador.compareTo(otro.getId_procesador());
    }
}
