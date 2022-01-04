package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Ranking extends JFrame{
    private PanelSur botonesInferior;
    private ListaJugadores listaJugadores;
    
    /**
     * Constructor de ranking 
     */
    public Ranking(BuscarRatonModel model){
        
        super("Ranking"); 
        
        //Botones Inferior
        botonesInferior = new PanelSur();
        listaJugadores = new ListaJugadores(model);
        listaJugadores.mostrar();
        // Icono del Jframe y minimizado
        Image img = Toolkit.getDefaultToolkit().getImage("archivos/Icono.png");
        ImageIcon imgicon = new ImageIcon(img);
        this.setIconImage(img);
        
        //ANYADE TODOS LOS COMPONETES AL FRAME
        this.add(listaJugadores,BorderLayout.CENTER);
        this.add(botonesInferior, BorderLayout.SOUTH);
        
        // Fija el tamaño de la ventana
        setSize(500,300);
        // Visualiza la ventana
        setVisible(true);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
    *   ACTION LISTENERS
     * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
        botonesInferior.setActionListener(actionListener);
        
    }
}
