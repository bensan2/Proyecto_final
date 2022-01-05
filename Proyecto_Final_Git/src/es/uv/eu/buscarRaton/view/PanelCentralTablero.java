package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import es.uv.eu.buscarRaton.model.Celdas;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    private String  raton;
    private Color color_celda,color_fondo;
        
    /**
    * Contructor panel con una matriz de botones juego "Buscar Raton"
    * @param model se necesita conocer las filas y columnas
    */
    
    public PanelCentralTablero(BuscarRatonModel model ){
           
        filas = model.getFilas();
        columnas = model.getColumnas();
        color_celda = model.getColorCelda();
        color_fondo = model.getColorFondo();
        raton = model.getRaton();
        
        this.setBackground(color_fondo);
            
        this.setLayout(new GridLayout(filas,columnas));
            
        dibujarBotones(filas, columnas);  
    }
    // Creacion del tablero matriz de botones
    private void dibujarBotones(int filas, int columnas){
        tablero = new JButton[filas][columnas];
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                tablero[x][y] = new JButton();          
                // Proporciona nombre al boton en este caso numerico
                tablero[x][y].setName(x+","+y);
                tablero[x][y].setBackground(color_celda);
                tablero[x][y].setContentAreaFilled(false);
                tablero[x][y].setOpaque(true);
                tablero[x][y].setActionCommand("Matriz");
                tablero[x][y].setVisible(true);
                add(tablero[x][y]);
            }
        }   
    }
    
    /**
     * Cambia y desactiva el boton de la matriz 
     */
    
    // Selecciona una celda
    public void selectCelda(int fila, int columna, Celdas celdas, boolean asistente){
        if(celdas.isRaton()){
            
            // Redimensiona la imagen a mostrar al tamaño del boton
            ImageIcon imagen = new ImageIcon(raton);
            Icon imagen_raton = new ImageIcon(imagen.getImage().getScaledInstance(tablero[fila][columna].getWidth(), 
                                                                                  tablero[fila][columna].getHeight(),
                                                                                  Image.SCALE_DEFAULT));
            tablero[fila][columna].setBackground(color_fondo);
            tablero[fila][columna].setIcon(imagen_raton);
            tablero[fila][columna].setEnabled(false);
        }else{
            if(celdas.getPistas() == 0){
                tablero[fila][columna].setBackground(color_fondo);
                tablero[fila][columna].setText("");
                tablero[fila][columna].setEnabled(false);
            }else if(asistente){
                
                tablero[fila][columna].setBackground(color_fondo);
                tablero[fila][columna].setText(celdas.getPistas()+"");
                tablero[fila][columna].setEnabled(false);
            }else {
                tablero[fila][columna].setBackground(color_fondo);
                tablero[fila][columna].setText("");
                tablero[fila][columna].setEnabled(false);
            }
        }
    }

    public void ReiniciarBotones(){
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                        // Cambia a color de color_celda, color sin pulsar
                        tablero[x][y].setBackground(color_celda);
                        // Activa el boton
                        tablero[x][y].setEnabled(true);
                    }
             }
    }
    
    
    public void DesactivarTablero(){
        for(int x = 0; x < filas; x++){
            for (int y = 0; y < columnas; y++){
                tablero[x][y].setEnabled(false);
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