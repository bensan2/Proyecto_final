package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class ListaJugadoresRanking extends JPanel {
    private BuscarRatonModel model;
    private JTextArea textArea;
    private JScrollPane scroll;
    
    public ListaJugadoresRanking( BuscarRatonModel model){
        
        this. model = model;
        
        setLayout(new BorderLayout());
        textArea = new JTextArea(20,26);
        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Sans", Font.BOLD, 12));
        scroll = new JScrollPane(textArea);
        
        this.add(scroll, BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }
    
    public void mostrar(){
        textArea.append("\t"+"Posicion" + "\t"+ "Jugador" + "\t"+"Puntos"+ "\n"+ 
                        model.Mostrar10MejoresJugadores());
    }
    
}