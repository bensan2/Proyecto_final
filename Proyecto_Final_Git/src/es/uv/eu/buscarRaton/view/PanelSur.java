
package es.uv.eu.buscarRaton.view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class PanelSur extends JPanel {
    private JButton cerrar;
    private JButton juego_nuevo;
    private JButton juego_configurar;
    
    public PanelSur() {
        juego_nuevo = new JButton("Juego Nuevo");
        juego_nuevo.setActionCommand("Resetear");
        add(juego_nuevo);
        
        juego_configurar = new JButton("Configurar Juego Nuevo");
        juego_configurar.setActionCommand("ItemNueva_configuacion");
        add(juego_configurar);
        
        cerrar = new JButton("Cerrar");
        cerrar.setActionCommand("Cerrar_Ranking");
        add(cerrar);
    }
    public void setActionListener(ActionListener actionListener){
            juego_nuevo.addActionListener(actionListener);
            juego_configurar.addActionListener(actionListener);
            cerrar.addActionListener(actionListener);
    }
    
}
