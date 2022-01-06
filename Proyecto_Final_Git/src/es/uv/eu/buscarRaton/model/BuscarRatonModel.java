package es.uv.eu.buscarRaton.model;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class BuscarRatonModel {
    
    // Variables basicas del juego
    private String nombre_jugador;
    private String raton;
    private Color color_celda;
    private Color color_fondo;
    private int puntos;
    private boolean asistente;

    // Tablero de juego
    private int filas, columnas;
    private Tablero[][] tablero; ///> filas x columnas
    private boolean celda_raton; ///> Donde esta el raton localizado

    // auxiliares para guardar datos iniciales
    private boolean asistente_inicial;
    
    // Array de todos los jugadores
    private ArrayList<Jugador> jugadores;
    
    // Para tratar imagenes
    private BufferedImage imagen;
    private String imagenFileName = "";

 
    /**
     * Funcion constructora para una nueva partida
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
    
    /**
     * Funcion que resetea la partida con los datos iniciales
     */
    public void Reset(){
        this.puntos = filas * columnas;
        asistente = asistente_inicial;
        initCeldas( filas,  columnas);
    }
    
    /**
     * Inicia un Tablero con filas x columnas.
     * Genera la posicion del raton en el tablero.
     * @param filas
     * @param columnas 
     */
    public void initCeldas( int filas, int columnas){
        setTablero(new Tablero[filas][columnas]);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                getTablero()[i][j] = new Tablero(i,j);        
            }
            
        }
        generarRaton(filas, columnas);
    }
    
    /**
     * Genera la posicion del raton en un lugar aleatorio del tablero
     * @param filas
     * @param columnas 
     */
    private void generarRaton(int filas, int columnas){
        int auxFila = (int) (Math.random() * filas);
        int auxColumna = (int) (Math.random() * columnas);
        if(!getTablero()[auxFila][auxColumna].isRaton()){
            getTablero()[auxFila][auxColumna].setRaton(true);
        }
        Pistas(filas, columnas);
    }
    
    /**
     * Generar Pistas para cuando el asistente este activado
     */
    private void Pistas(int filas, int columnas){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (getTablero()[i][j].isRaton()){
                    List<Tablero> celdasAlredeor = obtenCeldas(i,j);
                    celdasAlredeor.forEach((c)-> c.increPistas()); 
                }
            }
        }
    }
    
    /**
     * Obtener Tablero al rededor
     * @param fila
     * @param columna
     * @return listaCeldas
     */
    private List<Tablero> obtenCeldas(int fila, int columna){
        List<Tablero> listaCeldas = new LinkedList<>();
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
            
            if((auxFila >=0 && auxFila<this.getTablero().length) 
                    && auxColumna >=0 && auxColumna<this.getTablero()[0].length){
                listaCeldas.add(this.getTablero()[auxFila][auxColumna]);
        
            }
        }
        
        return listaCeldas;
    }
    
    
    /**
     * Guarda la partida del arraylist de jugador actual en un archivo historico.txt
     */
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
    
     /**
     * Lee la partida en un archivo historico.txt y las pasa a un ArrayList de jugadores
     */
    public void LeerPartidas(){
        this.jugadores = new ArrayList<Jugador>();
        
        Jugador aux_jugador = new Jugador(getNombreJugador(),puntos );
        File fichero = new File("archivos\\historico.txt");
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(fichero));
            String linea;
            int i = 0;
            while((linea = entrada.readLine()) != null){
                String datos[] = linea.split("\\|");
                String auxNombre = datos[0];                
                int auxPuntos = Integer.parseInt(datos[1]);
                jugadores.add(new Jugador(auxNombre,auxPuntos));                                              
                i++;
            }
            entrada.close();
            System.out.println("Fichero Leeido");
            
	} catch (IOException ex) {
            System.out.println("No puedo guardar: " + ex.getMessage());
	}
    }

    /**
     * Esta el raton en esa celca, boolean
     * @return celda_raton
     */
    public boolean isCeldas_raton() {
        return celda_raton;
    }

    /**
     * @param celdas_raton the celda_raton to set
     */
    public void setCeldas_raton(boolean celdas_raton) {
        this.celda_raton = celdas_raton;
    }

    /**
     * Get devuelve el tablero
     * @return 
     */
    public Tablero[][] getTablero() {
        return tablero;
    }

    /**
     * Asigna un tablero
     * @param tablero 
     */
    public void setTablero(Tablero[][] tablero) {
        this.tablero = tablero;
    }
    
    /**
     * Asigna un nomnre al jugador
     * @param _nombre_jugador 
     */
    public void setNombreJugador(String _nombre_jugador){
        nombre_jugador = _nombre_jugador;
    }
    
    /**
     * Devuelve el nombre del jugador
     * @return 
     */
    public String getNombreJugador(){
        return nombre_jugador;
    }
    
    /**
     * Asigna un raton
     * @param _raton 
     */
    public void setRaton(String _raton){
        raton = _raton;
    }
    
    /**
     * Devuelve un String con la localiacion de la imagen del raton
     * @return raton
     */
    public String getRaton(){
        if (null != this.raton) switch (this.raton) {
            case "Mickey":
                raton = "archivos/Raton1.jpg";
                break;
            case "Rasca":
                raton = "archivos/Raton2.png";
                break;
            case "Jerry":
                raton = "archivos/Raton3.png";
                break;
            case "Examinar...":
                raton = "archivos/Raton4.jpg";
                break;       
            default:
                break;
        }
        return raton;
    }
    
            
    /**
     * Carga en el sistema una imagen obtenida por el usuario en una direccion
     * Funcion reutilizada de la practica 4
     * @param imagenFile archivo imagen obtenida por el usuario
     */
    public void loadImagen(File imagenFile) {
        File raton4;
        if (imagenFile != null) {
            this.imagenFileName = imagenFile.getName();
            try {
                imagen = ImageIO.read(imagenFile);
                raton4 = new File(imagenFileName);
            }
            catch (IOException e) {
                System.out.println("Problemas leyendo la imagen '" + this.imagenFileName + "'.");
                System.out.println("Motivo: " + e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * Guarda la imagen. Reutilizado de la practica 4
     * @param imagenFile ruta donde guarda el archivo imagen
     */
    public void saveImagen() {     
        File ruta = new File("archivos/Raton4.jpg");
        if (ruta != null) {
            try {
                this.imagenFileName = ruta.getName();
                ImageIO.write(imagen,"jpg",ruta);
            } 
            catch (IOException e) {
                System.out.println("Problemas guardando la imagen '" + ruta.getName() + "'.");
                System.out.println("Motivo: " + e.getLocalizedMessage());
            }
        }
    }
  
    /**
     * Asigna color actual de la celda
     * @param _color_celda 
     */
    public void setColorCelda(Color _color_celda){
        color_celda = _color_celda;
    }
    
    /**
     * Devuelve el color actual de la celda
     * @return color_celda
     */
    public Color getColorCelda(){
        return color_celda;
    }
    
    /**
     * Asigna el color de fondo del fondo
     * @param _color_fondo 
     */
    public void setColorFondo(Color _color_fondo){
        color_fondo = _color_fondo;
    }
    
    /**
     * Devuelve el color actual del fono
     * @return color_fondo
     */
    public Color getColorFondo(){
        return color_fondo;
    }
    
    /**
     * Asigna asistente 
     * @param _asistente 
     */
    public void setAsistente(boolean _asistente){
        asistente = _asistente;
    }
    
    /**
     * Asigna las filas del tablero
     * @param filas 
     */
    public void setFilas(int filas){
        this.filas = filas;
    }
    
    /**
     * Asigna las columnas del tablero
     * @param columnas 
     */
    public void setColumnas (int columnas){
        this.columnas = columnas ;
    }
    
    /**
     * Devuelve si esta o no activado el asistente
     * @return 
     */
    public boolean getAsistente(){
        return asistente;
    }
  
    /**
     * Devuelve las filas actuales del tablero 
     * @return filas
     */
    public int getFilas(){
        return filas;
    }
    
    /**
     * Devuelve las columnas actuales del tablero
     * @return columnas
     */
    public int getColumnas(){
        return columnas;
    }
    
    /**
     * Devuelve los puntos actuales del tablero
     * @return puntos
     */
    public int getPuntos(){
        return puntos;
    }

    /**
     * Devuelve el ArrayList de todos los jugadores 
     * @return jugadores
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Asigna un ArrayList completo de jugadores
     * @param jugadores 
     */
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    /**
     * Muestra los 10 mejores jugadores
     * @return resultado array con los 10 mejores jugadores con posicion, nombre y puntos
     */
    public String Mostrar10MejoresJugadores(){
        
        String resultado="";
        OrdenarRanking();
        for (int i = 0; i < 10; i++) {
            resultado = resultado+ "\t"+ (i+1) +"\t"+ jugadores.get(i);
        }
        
        return resultado; 
    }
    
    /**
     * Ordena el array list de jugadores de mejor a peor conforme a los puntos
     */
    public void OrdenarRanking(){
        Collections.sort(jugadores, new Comparator<Jugador>() {
                @Override
                public int compare(Jugador p1, Jugador p2) {
                        // Aqui esta el truco, ahora comparamos p2 con p1 y no al reves como antes
                        return new Integer(p2.getPts()).compareTo(new Integer(p1.getPts()));
                }
        });
        }
    
    /**
     * SORPRESA de los programadores tambien llamado EasterEgg
     * Abre un enlace de youtube con gatitos graciosos
     */
    public void EasterEgg(){
                                    
        Desktop enlace=Desktop.getDesktop();
        try {
            enlace.browse(new URI("https://www.youtube.com/watch?v=BuOADNq99Ms"));
        }   catch (IOException | URISyntaxException e) {e.getMessage();}
    }
    
}