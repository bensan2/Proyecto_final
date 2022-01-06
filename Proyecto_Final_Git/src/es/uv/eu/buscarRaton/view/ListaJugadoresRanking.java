package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class ListaJugadoresRanking extends JPanel {
    private BuscarRatonModel model;
    private JTextArea textArea;
    
    /**
     * Contructor Lista Jugadores
     * @param model 
     */
    public ListaJugadoresRanking( BuscarRatonModel model){
        
        this. model = model;
        
        setLayout(new BorderLayout());
        textArea = new JTextArea(20,26);
        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Sans", Font.BOLD, 12));
        
        this.add(textArea, BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
    /**
     * Muestra los 10 mejores jugadores
     */
    public void mostrar(){
        textArea.append("\t"+"POSICION" + "\t"+ "JUGADOR" + "\t"+"PUNTOS"+ "\n"+ 
                        model.Mostrar10MejoresJugadores());
    }
    
}