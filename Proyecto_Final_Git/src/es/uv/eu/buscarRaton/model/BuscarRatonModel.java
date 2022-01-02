package es.uv.eu.buscarRaton.model;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
    private int puntos;
    private boolean asistente = true;
    
// Una clase tablero que tendria filas y columnas
    private int filas = 50, columnas = 50;
    private Celdas[][] celdas;
    private boolean celdas_raton; // Donde esta el raton localizado
    private boolean[][] celdas_tapadas; // todas empiezan en true
    private boolean[][] celdas_destapadas; // todas empiezan en false ///> cambiar boton a no poder pulsarlo para que no se poeda activar su action
    
// El ranking nombre[i] puntos[i] son correspondientes al usuario
    private String[] ranking_nombre;
    private String[] ranking_puntos;

    
    // auxiliares para guardar datos iniciales
    private int pts_iniciales;
    private boolean asistente_inicial;
    
    private ArrayList<Jugador> jugadores;

    
    
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
        initCeldas(filas,columnas);
        
        // Variables para guardar datos iniciales para reset
        pts_iniciales = this.puntos;
        asistente_inicial = this.asistente;
    }
    
/**
 * Descuenta los puntos actuales confome a cada jugada dependiendo si esta
 * la ayuda del asistente activo (-2 pts) o desactivado (-1pts)
 */
    public void DescontarPuntos(){
        if (asistente){
            puntos += -2;
        }  
        else{
            puntos += -1;
        }
    }
    
    public void Reset(){
        puntos = filas * columnas;
        asistente = asistente_inicial;
    }
    
    public void initCeldas( int filas, int columnas){
        setCeldas(new Celdas[filas][columnas]);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                getCeldas()[i][j] = new Celdas(i,j);        
            }
            
        }
        generarRaton(filas, columnas);
    }
    
    //Generar raton Random
    private void generarRaton(int filas, int columnas){
        int auxFila = (int) (Math.random() * filas);
        int auxColumna = (int) (Math.random() * columnas);
        getCeldas()[auxFila][auxColumna].setRaton(true);
        Pistas(filas, columnas);
        
    }
    
    // Obtener celdas al rededor
    private List<Celdas> obtenCeldas(int fila, int columna){
        List<Celdas> listaCeldas = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int auxFila = fila;
            int auxColumna = columna;
            switch(i){
                case 0: auxFila--; break; //Arriba
                case 1: auxFila--; break; //Arriba Derecha
                case 2: auxColumna++; break; //Derecha
                case 3: auxColumna++; auxFila++; break; //Derecha Abajo
                case 4: auxFila++; break; //Abajo
                case 5: auxColumna--; auxFila++;break; //Abajo Izquierda
                case 6: auxColumna--; break; //Izquierda
                case 7: auxColumna--; auxFila--; break; //Izquierda Arriba
            }
            if((auxFila >=0 && auxFila<this.getCeldas().length) 
                    && auxColumna >=0 && auxColumna<this.getCeldas()[0].length){
                listaCeldas.add(this.getCeldas()[auxFila][auxColumna]);
        
            }
        }
        
        return listaCeldas;
    }
    
    // Generar Pistas
    private void Pistas(int filas, int columnas){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (getCeldas()[i][j].isRaton()){
                    List<Celdas> celdasAlredeor = obtenCeldas(i,j);
                    celdasAlredeor.forEach((c)-> c.increPistas());
                    
                }
            }
        }
    }
    
       /*** Graba las partidas del ArrayList a Fichero. ***/    
    public void GuardarPartida(){

                // Grabar a Fichero
        	String linea;
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("archivos/historico.txt");
			// Escribimos linea a linea en el fichero
			for (int i=0;i<jugadores.size();i++) {
                                linea=jugadores.get(i).getNombre();
				fichero.write(linea+"\r\n");
                                linea=String.valueOf(jugadores.get(i).getPts());
				fichero.write(linea+"\r\n");
                                linea=String.valueOf(jugadores.get(i).getPts());
				fichero.write(linea+"\r\n");                           
			}
			fichero.close();
		} catch (Exception ex) {
			System.out.println("No puedo guardar: " + ex.getMessage());
		}
    }
    
    /*** Lee las partidas del Archivo y las pasa al ArrayList. ***/
    public void LeerPartidas(){
        // Fichero del que queremos leer
                int i = 1;
                String nom_historico ="";int pts_historico = 0;
		File fichero = new File("archivos/historico.txt");
		Scanner s = null;
                Jugador j = new Jugador("",0);
		try {
			// Leemos el contenido del fichero
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
                            if (i==1)
                            {nom_historico = s.nextLine(); 	
                             i=2;}
                            else
                                if (i==2){
                                    pts_historico = Integer.parseInt(s.nextLine()); 	
                                    i=3;
                                }
                                else {
                                    jugadores.add(new Jugador(nom_historico,pts_historico));
                                    i=1;
                                }
			}

		} catch (Exception ex) {
			System.out.println("No leo archivo: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("No cierro archivo: " + ex2.getMessage());
			}
		}
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

    /**
     * @param celdas_raton the celdas_raton to set
     */
    public void setCeldas_raton(boolean celdas_raton) {
        this.celdas_raton = celdas_raton;
    }

    /**
     * @return the celdas
     */
    public Celdas[][] getCeldas() {
        return celdas;
    }

    /**
     * @param celdas the celdas to set
     */
    public void setCeldas(Celdas[][] celdas) {
        this.celdas = celdas;
    }
}