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
     
    private int contador_temp;
        
    /**
    * Contructor panel con una matriz de botones juego "Buscar Raton"
    * @param model se necesita conocer las filas y columnas
    */
    
    public PanelCentralTablero(BuscarRatonModel model){
           
        filas = model.getFilas();
        columnas = model.getColumnas();
        raton = 1;
        this.setBackground(model.getColorFondo());
            
        this.setLayout(new GridLayout(filas,columnas));
            
        // Creacion del tablero matriz de botones
        tablero = new JButton[filas][columnas];
        for(int x= 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                tablero[x][y] = new JButton(""+contador_temp);
                 
                tablero[x][y].setBackground(model.getColorCelda());
                tablero[x][y].setContentAreaFilled(false);
                tablero[x][y].setOpaque(true);
                tablero[x][y].setActionCommand("Matriz");
                add(tablero[x][y]);
                contador_temp++;
            }
        }           
    }
    
    private void generarRaton(){
       int postfila = (int) (Math.random()*tablero.length);
       int postcolu = (int) (tablero[0].length*Math.random());
       // tablero[postfila][postcolu],is;
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
