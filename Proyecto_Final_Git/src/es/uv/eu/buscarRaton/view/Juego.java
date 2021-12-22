package es.uv.eu.buscarRaton.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author Benjamin Sanchez Monreal
 * @author Kevin Daniel Baguian Nsue
 * @version 1.0 2021/11/25
 */
public class Juego extends JFrame{
        
    private PanelArribaDatos panel_datos;
    private PanelCentralTablero panel_tablero;
    private JButton salir;
    private MenuBar menu_bar;

    
    /*
    * Constructor
    */
    public Juego(BuscarRatonModel model){
        
        super("Busca al Raton");  
        
        // Definimos BorderLayour
        this.setLayout(new BorderLayout());
        
        // Anyade MenuBar a la aplicacion
        menu_bar = new MenuBar();
        this.setJMenuBar(menu_bar);
        
        // Anyade el panel con los datos actuales del jugador
        panel_datos = new PanelArribaDatos();
        // Anyade el panel con las celdas tapadas y destapadas correspondientes
        panel_tablero = new PanelCentralTablero();
        // Anyade el boton salir como indica la practica
        salir = new JButton("Salir");
        salir.setActionCommand("Salir");

        // Agrega los paneles al BorderLayout
        add(panel_datos, BorderLayout.NORTH);
        add(panel_tablero, BorderLayout.CENTER);
        add(salir,BorderLayout.SOUTH);
        
        // Fija el tamaño de la ventana
        setSize(500,500);
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
        salir.addActionListener(actionListener);
        menu_bar.setActionListener(actionListener);
        panel_datos.setActionListener(actionListener);
        
    }
}
