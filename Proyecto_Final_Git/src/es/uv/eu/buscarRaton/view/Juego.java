package es.uv.eu.buscarRaton.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import es.uv.eu.buscarRaton.model.Tablero;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
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

    
    /**
    * Constructor de la ventana principal del juego
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
        
        // Icono del Jframe y minimizado
        Image img = Toolkit.getDefaultToolkit().getImage("archivos/Icono.png");
        ImageIcon imgicon=new ImageIcon(img);
        this.setIconImage(img);
        
        // Fija el tamaño de la ventana
        setSize(500,500);
        // Visualiza la ventana
         setVisible(true);
        // No puede cambiar de tamaño
        setResizable(true);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Centra la ventana en la pantalla
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - 
                            getSize().width) / 2, 
                    (Toolkit.getDefaultToolkit().getScreenSize().height - 
                            getSize().height) / 2);
    } 
    
    /**
     * Actualiza la vista con los elementos del juego
     * @param pts
     * @param celdas
     * @param fila
     * @param columna
     * @param asistente 
     */
    public void repaintJuego(int pts, Tablero celdas, int fila, int columna, boolean asistente){
        panel_datos.setPuntosActuales(pts);
        panel_tablero.selectCelda(fila, columna, celdas, asistente);
    }
    

    
    /**
     * Desactiva los botones
     */
    public void DesactivarTablero(){
        panel_tablero.DesactivarTablero();
    }
    
    /**
    * ACTION LISTENERS
    * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
        menu_bar.setActionListener(actionListener);
        panel_datos.setActionListener(actionListener);
        panel_tablero.setActionListener(actionListener);
        salir.addActionListener(actionListener);
    }
}