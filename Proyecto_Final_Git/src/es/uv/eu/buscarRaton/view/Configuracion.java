package es.uv.eu.buscarRaton.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class Configuracion extends JFrame {
    
    private final int MIN = 2;
    private final int MAX = 50; 
   
    private JButton bempezar, bcerrar;
    
    private JLabel lNombre,lRaton,lColor_celda,lColor_fondo,lAsistente,lColumnas,lFilas;
    private JTextField txtJugador,txtFilas,txtColumnas;
        
    private JButton bColor_fondo,bColor_celda;
    private Color color_fondo, color_celda;

    private JComboBox raton;
    private String[] ratones = {"Raton1", "Raton2", "Raton3"};
    
    private JComboBox asistente;
    private String[] asistente_respuestas = {"No", "Si"};
    
    JColorChooser ventanaDeColores;
    
    /**
     * Constructor de configuracion
     */
    public Configuracion(){
        
        this.setTitle("Configuracion de partida");
        this.setLayout(new GridLayout(8,2));
        
        lNombre = new JLabel("Nombre:", SwingConstants.CENTER);
        this.add(lNombre);
        
        txtJugador = new JTextField("USER_Default",5);
        txtJugador.setSize(25,40);
        txtJugador.setBounds(1,1,1,1);
        this.add(txtJugador);    
        
        
        lFilas = new JLabel("Filas:", SwingConstants.CENTER);
        this.add(lFilas);
        
        txtFilas = new JTextField("2",2);
        txtFilas.setSize(5,5);
        txtFilas.setBounds(1,1,1,1);
        this.add(txtFilas); 
        
        
        lColumnas = new JLabel("Columnas:", SwingConstants.CENTER);
        this.add(lColumnas);
        
        txtColumnas = new JTextField("2",2);
        txtColumnas.setSize(5,5);
        txtColumnas.setBounds(1,1,1,1);
        this.add(txtColumnas); 
        
        lAsistente = new JLabel("Asistente:", SwingConstants.CENTER);
        this.add(lAsistente);
        
        asistente = new JComboBox(asistente_respuestas);
        this.add(asistente);
        
                lRaton = new JLabel("Raton:", SwingConstants.CENTER);
        this.add(lRaton);
        
        raton = new JComboBox(ratones);
        this.add(raton);          
        
        
        lColor_celda = new JLabel("Color de celda:", SwingConstants.CENTER);
        this.add(lColor_celda);

        bColor_celda = new JButton("Pulse aqui");
        bColor_celda.setActionCommand("ColorCelda");
        this.add(bColor_celda);
        
        
        lColor_fondo = new JLabel("Color de fondo:", SwingConstants.CENTER);
        this.add(lColor_fondo);
        
        bColor_fondo = new JButton("Pulse aqui");
        bColor_fondo.setActionCommand("ColorFondo");
        this.add(bColor_fondo);
        
        
        bcerrar = new JButton("SALIR");
        bcerrar.setActionCommand("Salir");
        this.add(bcerrar);
        
        bempezar = new JButton("EMPEZAR");
        bempezar.setActionCommand("Empezar");
        this.add(bempezar);
 
        
        // Fija el tamaño de la ventana
        setSize(300,300);
        // Visualiza la ventana
        setVisible(true);
        // No puede cambiar de tamaño
        setResizable(false);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
       
    
 /**
 GET DE LAS FUNCIONES   
     * @return 
 */
    public String getNombre(){
        return txtJugador.getText();
    }
    
    public String getRaton(){
        return raton.getSelectedItem().toString();
    }
    
    public Color getColorCelda(){
        return color_celda;
    }
    
    public Color getColorFondo(){
        return color_fondo;
    }
    
     public int getFilas(){
        return Integer.parseInt(txtFilas.getText());
    }
    
    public int getColumnas(){
        return Integer.parseInt(txtColumnas.getText());
    }
    
    public boolean getAsistente(){
        boolean aux_asistente;
        aux_asistente = "Si".equals(asistente.getSelectedItem().toString());
        return aux_asistente;
    }
    
    public void setColorCelda(){
        color_celda = ventanaDeColores.showDialog(null, "Seleccione un Color", Color.GREEN);
        bColor_celda.setBackground(color_celda);
    }
    
    public void setColorFondo(){
        color_fondo=ventanaDeColores.showDialog(null, "Seleccione un Color", Color.GRAY);
        bColor_fondo.setBackground(color_fondo);
    }
    
    /**
    *    Funcion para comprobar que los datos son correctos
    *    @return datos_correctos false si algun dato es erroneo
    */
    public boolean DatosCorrectos(){
        boolean datos_correctos = true;
        
        // Nombre usuario no esta vacio
        switch (txtJugador.getText()){
            case"":
                JOptionPane.showMessageDialog(null,"Introduzca un nick en" +
                                                   " el campo nombre");
                datos_correctos = false;
            break;
        }
        
        // Es un valor numerico
        try{
            Integer.parseInt(txtFilas.getText());
            Integer.parseInt(txtColumnas.getText());            
        } catch(NumberFormatException ex){
            datos_correctos = false;
             JOptionPane.showMessageDialog(null,"El valor de filas y columnas"
                     + "a de ser numerico entre 2 al 50" );
        }

        // Minimo 2 maximo 50 para las filas y columnas
        if (getFilas()    < MIN || getFilas()    > MAX ||
            getColumnas() < MIN || getColumnas() > MAX) {
                JOptionPane.showMessageDialog(null, "Minimo 2x2" + "\n" +
                                                    "Maximo 50x50" );
            datos_correctos = false;
        }
        
        // Colores elegidos tienen que ser distintos
        if (getColorCelda().equals( getColorFondo() ) ){
            JOptionPane.showMessageDialog(null,"El color de la Celda y " +
                                               "el color del Fondo" +"\n" +
                                               "Deben ser distintos" );
            datos_correctos = false;
        }
        
        return datos_correctos;
    }
            
    
    /**
    *   ACTION LISTENERS
     * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
            bcerrar.addActionListener(actionListener);
            bempezar.addActionListener(actionListener);
            bColor_celda.addActionListener(actionListener);
            bColor_fondo.addActionListener(actionListener);
    }
}