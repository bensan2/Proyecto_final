package es.uv.eu.buscarRaton.view;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class PanelArribaDatos extends JPanel{
    
    private JLabel lNombre,lPts,lAsistente;
    private JLabel lnombre_jugador,lpts_actuales;
    // private String nombre_jugador="UsuarioTem",pts_actuales="10"; // se saca del modelo,cambiarlo,es temporal
    
    private boolean asistente = new Boolean(true); // se saca del modelo,cambiarlo,es temporal
    
    private JRadioButton rAsistente_si,rAsistente_no;
    private ButtonGroup gAsistente;
    private BuscarRatonModel model;
    
    /**
     *
     * @param model
     */
    public PanelArribaDatos(BuscarRatonModel model){
        this.setLayout(new FlowLayout());
        this.model = model;
        
        lNombre = new JLabel("Nombre:", SwingConstants.CENTER);
        this.add(lNombre);
        
        lnombre_jugador = new JLabel(model.getNombreJugador(), SwingConstants.CENTER);
        this.add(lnombre_jugador);
        
        lPts = new JLabel("Pts:", SwingConstants.CENTER);
        this.add(lPts);  
        
        lpts_actuales = new JLabel(String.valueOf(model.getPuntos()), SwingConstants.CENTER);
        this.add(lpts_actuales);
        
        
        lAsistente = new JLabel("Asistente:", SwingConstants.CENTER);
        this.add(lAsistente);
        
        rAsistente_si = new JRadioButton("Si",asistente);
        rAsistente_si.setActionCommand("Activar_asistente");
        rAsistente_no = new JRadioButton("No",!asistente);
        rAsistente_no.setActionCommand("Desactivar_asistente");
        this.add(rAsistente_si);
        this.add(rAsistente_no);
      
        gAsistente = new ButtonGroup();
        gAsistente.add(rAsistente_si);
        gAsistente.add(rAsistente_no);

    }
    
    /**
    /*
    SETTERS
     * @param _asistente
    */
    /* public void setNombreJugador(String _nombre_jugador){
        nombre_jugador = _nombre_jugador;
    }
    
    /**
     * Asigna los puntos actuales, Cambia el tipo de int a String
     * @param _puntos_actuales int de los pts actuales
     *
    public void setPuntosActuales(int _puntos_actuales){
        pts_actuales = Integer.toString(_puntos_actuales);
    }
    */
    public void setAsistente(Boolean _asistente){
        asistente = _asistente;
    }
    
    /**
    *   ACTION LISTENERS
     * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
        rAsistente_si.addActionListener(actionListener);
        rAsistente_no.addActionListener(actionListener);
    }
}
