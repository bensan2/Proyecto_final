package es.uv.eu.buscarRaton.view;

import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class MenuBar extends JMenuBar{
    
    private JMenu configuracion;
    private JMenuItem nueva_configuracion;
    private JMenuItem resetear_partida;
    
    private JMenu ranking;
    private JMenuItem opcion_ranking;
    
    private JMenu accesibilidad;
    private JMenuItem lupa;
    
    private JMenu ayuda;
    private JMenuItem manual;
    private JMenuItem acercaDe;
    
    public MenuBar(){
        
        // Crea el menu de configuracion
        configuracion = new JMenu("Configuracion");
        // Creamos los items dentro del menu configuracion y asignamos actioncommand
        nueva_configuracion = new JMenuItem("Nueva Configuracion");
        nueva_configuracion.setActionCommand("Nueva_configuacion");
        resetear_partida = new JMenuItem("Resetear partida");
        resetear_partida.setActionCommand("Resetear");
        // Anyadimos los items al menu
        configuracion.add(nueva_configuracion);
        configuracion.add(resetear_partida);
        // Anyadimos el menu
        this.add(configuracion);

        ranking = new JMenu("Ranking");
        opcion_ranking = new JMenuItem("Ranking");
        opcion_ranking.setActionCommand("Ranking");
        ranking.add(opcion_ranking);
        this.add(ranking);

        accesibilidad = new JMenu("Accesibilidad");
        lupa = new JMenuItem("Lupa");
        lupa.setActionCommand("Lupa");
        accesibilidad.add(lupa);
        this.add(accesibilidad);
        
        ayuda = new JMenu("Ayuda");
        manual = new JMenuItem("Manual de juego");
        manual.setActionCommand("Manual");
        acercaDe = new JMenuItem("Acerca de...");
        acercaDe.setActionCommand("AcercaDe");
        ayuda.add(manual);
        ayuda.add(acercaDe);
        this.add(ayuda);
    }
    /**
     * ActionListeners
     * @param actionlistener 
     */
    public void setActionListener (ActionListener actionlistener) {
        nueva_configuracion.addActionListener(actionlistener);
        resetear_partida.addActionListener(actionlistener);
        opcion_ranking.addActionListener(actionlistener);
        lupa.addActionListener(actionlistener);
        manual.addActionListener(actionlistener);
        acercaDe.addActionListener(actionlistener);
    }
}
