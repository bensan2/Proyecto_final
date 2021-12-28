package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class PanelCentralTablero extends JPanel{
    
    private int filas, columnas;
    private JButton[][] tablero;
    private int  raton;
    private BuscarRatonModel model = new BuscarRatonModel();
     
    private int contador_temp = 0;
        
    /**
    * Contructor panel con una matriz de botones juego "Buscar Raton"
    * @param model se necesita conocer las filas y columnas
    */
    
    public PanelCentralTablero(){
           
        filas = model.getFilas();
        columnas = model.getColumnas();
        raton = 1;
        this.setBackground(model.getColorFondo());
            
        this.setLayout(new GridLayout(filas,columnas));
            
        // Creacion del tablero matriz de botones
        tablero = new JButton[filas][columnas];
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                tablero[x][y] = new JButton(""+contador_temp);
                
                // Proporciona nombre al boton en este caso numerico
                tablero[x][y].setName(new StringBuilder().append(contador_temp).toString());
                tablero[x][y].setBackground(model.getColorCelda());
                tablero[x][y].setContentAreaFilled(false);
                tablero[x][y].setOpaque(true);
                tablero[x][y].setActionCommand("Matriz");
                add(tablero[x][y]);
                contador_temp++;
            }
        }           
    }
    
    public void generarRaton(){
       int postfila = (int) (Math.random()*tablero.length);
       int postcolu = (int) (tablero[0].length*Math.random());
       // tablero[postfila][postcolu],is;
    }
    
    /**
     * Cambia y desactiva el boton de la matriz 
     * @param _num_boton nombre del boton correspondiente a un numero id 
     */
    public void setCambiarColorBoton(String _num_boton){
        
        // Recorre la matriz
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                    // .equals(nombre) compara como == pero de objetos
                    if (tablero[x][y].getName().equals(_num_boton)){
                        // Cambia a color de fondo
                        tablero[x][y].setBackground(model.getColorFondo());
                        // Desactiva el boton
                        tablero[x][y].setEnabled(false);
                    }
             }
        }
    }
    
    /**
    *   ACTION LISTENERS
    */ 
    public void setActionListener(ActionListener actionListener){
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                tablero[x][y].addActionListener(actionListener);
            }
        }
    }
}
