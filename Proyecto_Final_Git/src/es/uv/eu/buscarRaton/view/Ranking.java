package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Ranking extends JFrame{
    
    // Panel cental Ranking
    private ListaJugadoresRanking panel_Lista_Jugadores;

    // Botones inferiores
    private JPanel panel_sur;
    private JButton cerrar,juego_configurar,juego_nuevo;
        
    /**
     * Constructor de ranking 
     */
    public Ranking(BuscarRatonModel model){
        
        super("Ranking - 10 mejores jugadores"); 
        
        // Panel central Ranking jugadores
        panel_Lista_Jugadores = new ListaJugadoresRanking(model);
        panel_Lista_Jugadores.mostrar();
        
        // Panel inferior Botonera  
        panel_sur = new JPanel();
        
        cerrar = new JButton("Cerrar");
        cerrar.setActionCommand("Cerrar_Ranking");
        panel_sur.add(cerrar);
        
        juego_configurar = new JButton("Configurar Juego Nuevo");
        juego_configurar.setActionCommand("Nueva_configuacion_ranking");
        panel_sur.add(juego_configurar);
        
        juego_nuevo = new JButton("Juego Nuevo");
        juego_nuevo.setActionCommand("Resetear");
        panel_sur.add(juego_nuevo);        
        
        //ANYADE TODOS LOS COMPONETES AL FRAME
        this.add(panel_Lista_Jugadores,BorderLayout.CENTER);
        this.add(panel_sur, BorderLayout.SOUTH);
        
        // Icono del Jframe y minimizado
        Image img = Toolkit.getDefaultToolkit().getImage("archivos/Icono.png");
        ImageIcon imgicon = new ImageIcon(img);
        
        this.setIconImage(img);
        // Fija el tamaño de la ventana
        setSize(400,250);
        // Visualiza la ventana
        setVisible(true);
        // No puede cambiar de tamaño
        setResizable(false);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
    * ACTION LISTENERS
    * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){        
        juego_nuevo.addActionListener(actionListener);
        juego_configurar.addActionListener(actionListener);
        cerrar.addActionListener(actionListener);      
    }
}
