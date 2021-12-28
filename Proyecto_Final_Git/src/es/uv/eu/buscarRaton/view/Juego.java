package es.uv.eu.buscarRaton.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author Benjamin Sanchez Monreal
 * @author Kevin Daniel Baguian Nsue
 * @version 1.0 2021/11/25
 */
public class Juego extends JFrame{
    private BuscarRatonModel model;
    private MenuBar menu_bar;
    private PanelArribaDatos panel_datos;
    private PanelCentralTablero panel_tablero;
    private JButton salir;

    
    /*
    * Constructor
    */
    public Juego(BuscarRatonModel model){

        super("Busca al Raton");  
        this.model = model;
        // Definimos BorderLayour
        this.setLayout(new BorderLayout());
        
        // Anyade MenuBar a la aplicacion
        menu_bar = new MenuBar();
        this.setJMenuBar(menu_bar);
        
        // Anyade el panel con los datos actuales del jugador
        panel_datos = new PanelArribaDatos(model);
        // Anyade el panel con las celdas tapadas y destapadas correspondientes
        panel_tablero = new PanelCentralTablero(model);
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
        // setVisible(true);
        // No puede cambiar de tamaño
        setResizable(true);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    /**
     * Repaint de los elementos del juego
     */
    public void repaintJuego(int pts, String num_boton, Color _color_fondo){
        //panel_datos.setText(string_pulsado);
        panel_datos.setPuntosActuales(pts);
        panel_tablero.setBotonPulsado(num_boton,_color_fondo);
    }
    
    public void Reset(String _nombre, int pts, boolean asistente, Color color_celda){
        panel_datos.setNombreJugador(_nombre);
        panel_datos.setPuntosActuales(pts);
        panel_datos.setAsistente(asistente);
        panel_tablero.ReiniciarBotones(color_celda);
    }
    
    /**
    *   ACTION LISTENERS
     * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
        menu_bar.setActionListener(actionListener);
        panel_datos.setActionListener(actionListener);
        panel_tablero.setActionListener(actionListener);
        salir.addActionListener(actionListener);
    }
}
