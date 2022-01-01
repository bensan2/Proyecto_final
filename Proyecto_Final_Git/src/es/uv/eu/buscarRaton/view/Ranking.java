package es.uv.eu.buscarRaton.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Ranking extends JFrame{
    
    private JTextField ranking_jugadores ;
    
    private JButton cerrar,juego_nuevo, juego_configurar;
    
    /**
     * Constructor de ranking 
     */
    public Ranking(){
        
        super("Ranking");  
        this.setLayout(new GridLayout(8,2));
        
        
        ranking_jugadores = new JTextField("LINEAS DE EJEMPLO BORRAR");
        ranking_jugadores.setEditable(false);
        ranking_jugadores.setBackground(Color.lightGray);
        ranking_jugadores.setForeground(Color.black);
        ranking_jugadores.setFont(new Font("Sans", Font.BOLD, 12));
        add(ranking_jugadores);
        
        juego_nuevo = new JButton("Juego Nuevo");
        juego_nuevo.setActionCommand("Resetear");
        add(juego_nuevo);
        
        juego_configurar = new JButton("Configurar Juego Nuevo");
        juego_configurar.setActionCommand("ItemNueva_configuacion");
        add(juego_configurar);
        
        cerrar = new JButton("Cerrar");
        cerrar.setActionCommand("Cerrar_Ranking");
        add(cerrar);
        
        // Fija el tamaño de la ventana
        setSize(300,300);
        // Visualiza la ventana
        setVisible(true);
        // No puede cambiar de tamaño
        setResizable(true);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
    *   ACTION LISTENERS
    */ 
    public void setActionListener(ActionListener actionListener){
            juego_nuevo.addActionListener(actionListener);
            juego_configurar.addActionListener(actionListener);
            cerrar.addActionListener(actionListener);
    }
}
