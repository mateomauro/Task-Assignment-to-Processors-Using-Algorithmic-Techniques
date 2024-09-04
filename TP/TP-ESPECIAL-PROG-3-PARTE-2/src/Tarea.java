public class Tarea implements Comparable<Tarea>{
    private String id_tarea;
    private String nombre_tarea;
    private int tiempo_ejecucion;
    private boolean es_critica;
    private int nivel_prioridad;

    //CONSTRUCTOR
    public Tarea(String id_tarea, String nombre_tarea, int tiempo_ejecucion, boolean es_critica, int nivel_prioridad){
        this.id_tarea = id_tarea;
        this.nombre_tarea = nombre_tarea;
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.es_critica = es_critica;
        this.nivel_prioridad = nivel_prioridad;
    }

    public Tarea (Tarea t){
        new Tarea(t.id_tarea,t.nombre_tarea,t.getTiempo_ejecucion(),t.esCritica(),t.getNivel_prioridad());
    }

    public String getId_tarea() {
        return id_tarea;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public int getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public boolean esCritica() {
        return es_critica;
    }

    public int getNivel_prioridad() {
        return nivel_prioridad;
    }

    public Tarea copiar() {
        return new Tarea(this.id_tarea, this.nombre_tarea, this.tiempo_ejecucion, this.es_critica, this.nivel_prioridad);
    }

    public String toString(){
        return this.id_tarea;
    }

    @Override
    public int compareTo(Tarea t) {
        return Integer.compare(t.getTiempo_ejecucion(),this.getTiempo_ejecucion());
    }
}
