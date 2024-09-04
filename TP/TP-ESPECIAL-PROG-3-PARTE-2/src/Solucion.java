import java.util.ArrayList;
import java.util.Iterator;

public class Solucion{
    private ArrayList<Procesador> procesadores;
    private ArrayList<Tarea> visitados;
    private int cantEstados;


    public Solucion(){
        this.procesadores = new ArrayList<>();
        this.visitados = new ArrayList<>();
        this.cantEstados = 0;
    }

    public int getCantEstados(){
        return this.cantEstados;
    }

    public void setCantEstados(int valor){
        this.cantEstados = valor;
    }

    public Solucion (ArrayList<Procesador> p){
        this.procesadores = new ArrayList<>(p);
        this.visitados = new ArrayList<>();
    }

    public void agregarProcesador(Procesador pro){
        this.procesadores.add(pro);
    }

    public void agregarVisitado(Tarea tarea){
        this.visitados.add(tarea);
    }

    public void quitarVisitado(Tarea tarea){
        this.visitados.remove(tarea);
    }

    public int getSizeVisitados(){
       return this.visitados.size();
    }

    public ArrayList<Tarea> getVisitados(){
        return this.visitados;
    }

    public int getMayorTiempo(){
        int actual = 0;
        for (Procesador pro: this.procesadores){
            int tiempo = pro.gettEjecucion();
            if (tiempo > actual){
                actual = tiempo;
            }
        }
        return actual;
    }

    public boolean estoyVacio(){
        return this.procesadores.isEmpty();
    }

    public Solucion copiar() {
        Solucion copia = new Solucion();
        for (Procesador p : this.procesadores) {
            copia.agregarProcesador(p.copiar());
        }
        return copia;
    }
    public Iterator<Procesador> getProcesadores(){
        return this.procesadores.iterator();
    }
}
