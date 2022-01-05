package es.uv.eu.buscarRaton.model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class BuscarRatonModel {
    
    private String nombre_jugador;
    private String raton;
// public icono raton;
    private Color color_celda;
    private Color color_fondo;
    private int puntos;
    private boolean asistente;
    
// Una clase tablero que tendria filas y columnas
    private int filas, columnas;
    private Celdas[][] celdas;
    private boolean celdas_raton; // Donde esta el raton localizado
    private boolean[][] celdas_tapadas; // todas empiezan en true
    private boolean[][] celdas_destapadas; // todas empiezan en false ///> cambiar boton a no poder pulsarlo para que no se poeda activar su action
   
    // auxiliares para guardar datos iniciales
    private boolean asistente_inicial;
    
    private ArrayList<Jugador> jugadores,jugadores2;
    private ArrayList<String> nombres_jugadores;
    private ArrayList<String> pts_jugadores;

 
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
        this.puntos = filas * columnas;
        initCeldas(filas,columnas);
        
        // Variables para guardar datos iniciales para reset
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
        this.puntos = filas * columnas;
        asistente = asistente_inicial;
        initCeldas( filas,  columnas);
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
        if(!getCeldas()[auxFila][auxColumna].isRaton()){
            getCeldas()[auxFila][auxColumna].setRaton(true);
        }
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
                case 1: auxFila--; auxColumna++; break; //Arriba Derecha
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
        	String linea = getNombreJugador()+"|"+getPuntos();
		File fichero = new File("archivos\\historico.txt");
                
		try {
                        // Crea el Archivo si no existe.
                        PrintWriter salida = new PrintWriter(new FileWriter(fichero, true));
                        salida.println(linea);
			salida.close();
                        System.out.println("Fichero Generado");
		} catch (IOException ex) {
			System.out.println("No puedo guardar: " + ex.getMessage());
		}
    }
    
    /*** Lee las partidas del Archivo y las pasa al ArrayList. ***/
    public void LeerPartidas(){
        this.jugadores = new ArrayList<Jugador>();
        this.nombres_jugadores = new ArrayList<String>();
        this.pts_jugadores = new ArrayList<String>();
        
        Jugador aux_jugador = new Jugador(getNombreJugador(),puntos );
        File fichero = new File("archivos\\historico.txt");
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            int i = 0;
            while((linea = entrada.readLine()) != null){
                String datos[] = linea.split("\\|");
                String auxNombre = datos[0];
                String auxPuntos = datos[1];
                //int auxPuntos = Integer.parseInt(datos[1]);
                aux_jugador.setNombre(auxNombre);
                //aux_jugador.setPts(auxPuntos);
                jugadores.add(aux_jugador);
                                
                nombres_jugadores.add(auxNombre);
                pts_jugadores.add(auxPuntos);
                
                System.out.println(nombres_jugadores.get(i));
                System.out.println(pts_jugadores.get(i));
                
                /*
                // PARA COMPROBAR DATOS
                System.out.println("Leyendo los datos del Array de Jugadores: "+ jugadores.get(i).getNombre()+"\t"+jugadores.get(i).getPts());
                System.out.println("Jugador : "+ aux_jugador.getNombre()+"\t"+aux_jugador.getPts());
                // SOLO MUESTRA EL ULTIMO , parece ser que rellena todo el ArrayList con el aux_jugador en vece añadirlo al final del arraylist
                toString();
                */
                
                i++;
            }
            entrada.close();
            System.out.println("Fichero Leeido");
	} catch (IOException ex) {
            System.out.println("No puedo guardar: " + ex.getMessage());
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
        if (null != this.raton) switch (this.raton) {
            case "Raton1":
                raton = "archivos/ratonRedim1.jpg";
                break;
            case "Raton2":
                raton = "archivos/Raton2.png";
                break;
            case "Raton3":
                raton = "archivos/Raton3.png";
                break;
            default:
                break;
        }
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
     * @return the jugadores
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * @param jugadores the jugadores to set
     */
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    @Override
    public String toString(){
        String resultado="";
        String aux_nombre;
        int aux_pts;
        Jugador aux_jugador_toString = new Jugador("",0);
        this.jugadores2 = new ArrayList<Jugador>();

        
        // Recoge los datos correctamente
        for(int i = 0; i < nombres_jugadores.size(); i++){
            //System.out.println("NOMBRE: " + nombres_jugadores.get(i) + "\t" +"PUNTOS: "+ pts_jugadores.get(i));
            
            aux_nombre = nombres_jugadores.get(i);
            aux_pts = Integer.parseInt(pts_jugadores.get(i));
            
            aux_jugador_toString.setNombre(aux_nombre);
            aux_jugador_toString.setPts(aux_pts);
            
            // No agrega al final de la cola , sustituye todo el ArrayList con este valor 
            jugadores2.add(aux_jugador_toString);
        }
        // Ya que no podemos utilizar arraylist de jugadores, tendremos 2 arraylist relacionados de jugadores pts
        for (int i = 0; i < nombres_jugadores.size(); i++) {
            System.out.println("NOMBRE: " + nombres_jugadores.get(i) + "\t" +"PUNTOS: "+ pts_jugadores.get(i));
        }
        for (int i = 0; i < nombres_jugadores.size(); i++) {
            resultado = resultado+ "\t"+ (i+1) +"\t"+ nombres_jugadores.get(i) + "\t" + pts_jugadores.get(i) + "\n";
            System.out.println("toStringNOMBRE: " + nombres_jugadores.get(i) + "\t" +"PUNTOS: "+ pts_jugadores.get(i));
        }
        
        return resultado; 
    }
    
}