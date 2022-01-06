package es.uv.eu.buscarRaton.model;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Tablero {
    private int filas;
    private int columnas;
    private boolean raton;
    private int pistas;
    
    public Tablero(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
    }
    public void increPistas(){
        this.pistas++;
    }

    /**
     * @return the filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @return the columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @return the raton
     */
    public boolean isRaton() {
        return raton;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(int filas) {
        this.filas = filas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    /**
     * @param raton the raton to set
     */
    public void setRaton(boolean raton) {
        this.raton = raton;
    }

    /**
     * @return the pistas
     */
    public int getPistas() {
        return pistas;
    }

    /**
     * @param pistas the pistas to set
     */
    public void setPistas(int pistas) {
        this.pistas = pistas;
    }


    
    
}

