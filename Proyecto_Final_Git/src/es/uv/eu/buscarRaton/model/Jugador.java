package es.uv.eu.buscarRaton.model;


/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Jugador {

    private String nombre;
    private int pts;
    
    public Jugador (String _nombre, int _pts){
        this.nombre = _nombre;
        this.pts = _pts;
    }


    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }

    /**
     * @return the pts
     */
    public int getPts() {
        return pts;
    }

    /**
     * @param partidasGanadas the pts to set
     */
    public void setPts(int _pts) {
        this.pts = _pts;
    }

    public String toString(){
        return (nombre + "\t " + pts);
    }
}

