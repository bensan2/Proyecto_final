package es.uv.eu.buscarRaton.model;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class BuscarRatonModel {
    
    private String nombre_jugador="Jugador1";
    private String raton ="Raton1";
// public icono raton;
    private Color color_celda = Color.YELLOW;
    private Color color_fondo = Color.WHITE;
    private int puntos = 100;
    private boolean asistente = true;
    
// Una clase tablero que tendria filas y columnas
    private int filas = 5, columnas = 5;
    private boolean celdas_raton; // Donde esta el raton localizado
    private boolean[][] celdas_tapadas; // todas empiezan en true
    private boolean[][] celdas_destapadas; // todas empiezan en false ///> cambiar boton a no poder pulsarlo para que no se poeda activar su action
    
// El ranking nombre[i] puntos[i] son correspondientes al usuario
    private String[] ranking_nombre;
    private String[] ranking_puntos;

    
    // auxiliares para guardar datos iniciales
    private int pts_iniciales;
    private boolean asistente_inicial;
    
    
/*
GET Y SET DE LOS ATRIBUTOS 
*/
    public void setNombreJugador(String _nombre_jugador){
        nombre_jugador = _nombre_jugador;
    }
    
    public String getNombreJugador(){
        return nombre_jugador;
    }
    
    public void setRaton(String _raton){
        raton = _raton;
    }
    
    public String getRaton(){
        return raton;
    }
    
    public void setColorCelda(Color _color_celda){
        color_celda = _color_celda;
    }
    
    public Color getColorCelda(){
        return color_celda;
    }
    
    public void setColorFondo(Color _color_fondo){
        color_fondo = _color_fondo;
    }
    
    public Color getColorFondo(){
        return color_fondo;
    }
    
    public void setAsistente(boolean _asistente){
        asistente = _asistente;
    }
    public void setFilas(int filas){
        this.filas = filas;
    }
    
    public void setColumnas (int columnas){
        this.columnas = columnas ;
    }
    
    public boolean getAsistente(){
        return asistente;
    }

    public void setTablero(int _filas, int _columnas){
        filas = _filas;
        columnas = _columnas;
    }
  
    public int getFilas(){
        return filas;
    }
    
    public int getColumnas(){
        return columnas;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
/**
 * Contructor para una nueva partida
 * @param _nombre_jugador
 * @param _raton
 * @param _color_celda
 * @param _color_fondo
 * @param _asistente 
 * @param _filas
 * @param _columnas 
 */
    public void JuegoNuevo(String _nombre_jugador, String _raton, Color _color_celda,
            Color _color_fondo, boolean _asistente, int _filas, int _columnas){
 
        this.nombre_jugador = _nombre_jugador;
        this.raton = _raton;
        this.color_celda = _color_celda;
        this.color_fondo = _color_fondo;
        this.asistente = _asistente;
        this.filas = _filas;
        this.columnas = _columnas;
        this.puntos = filas + columnas;
        RatonPosicionAleatoria(raton);
        
        
        /* DEVUELVE EL COLOR CORRECTAMENTE
System.out.println("JUEGO NUEVO");
        System.out.println(nombre_jugador);
        System.out.println(raton);
        System.out.println(color_celda);
        System.out.println(color_fondo);
        System.out.println(asistente);
        System.out.println(filas);
        System.out.println(columnas);
        System.out.println(puntos);
*/
        
        // Variables para guardar datos iniciales para reset
        pts_iniciales = this.puntos;
        asistente_inicial = this.asistente;
    }
    
/**
 * Descuenta los puntos actuales confome a cada jugada dependiendo si esta
 * la ayuda del asistente activo (-2 pts) o desactivado (-1pts)
 */
    public void DescontarPuntos(){
        if (asistente == true){
            puntos += -2;
        }  
        else{
            puntos += -1;
        }
    }
    
    public void Reset(){
        puntos = pts_iniciales;
        asistente = asistente_inicial;
    }
    
    public void RatonPosicionAleatoria(String raton){
        // posicion aleatoria del raton
    }

    

    
    
    
    
    
/*
WARRING CAMBIAR  a como se diseñe al final   
*/ 
    public void setCeldasTapadas(boolean[][] _celdas_tapadas){
        celdas_tapadas = _celdas_tapadas;
    }
    
// Esto esta muy mal pensar bien 
    public boolean getCeldaTapada(boolean[][] _celda){
        if(_celda == celdas_tapadas)
            return true;
        else 
            return false;
    }
    
    public void setCeldasDestapadas(boolean[][] _celdas_destapadas){
        celdas_destapadas = _celdas_destapadas;
    }
    
// Esto esta muy mal pensar bien 
    public boolean getCeldaDestapada(boolean[][] _celda){
        boolean aux;
        if( celdas_destapadas == _celda)
            aux = true;
        else 
            aux = false;
        
        return aux;
    }

    
    // Metodos faltan
    /*
    public void CalcularRanking (String nombre, int puntos_actuales)
    */

    /**
     * @return the celdas_raton
     */
    public boolean isCeldas_raton() {
        return celdas_raton;
    }
}
