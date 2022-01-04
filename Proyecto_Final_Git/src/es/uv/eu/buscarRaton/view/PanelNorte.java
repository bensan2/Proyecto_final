/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uv.eu.buscarRaton.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class PanelNorte extends JPanel {
    private JLabel baraDatos;
    
    public PanelNorte(){
        baraDatos = new JLabel("\t#     \tNOMBRE    \tPUNTOS");
        add(baraDatos);
    }
}
