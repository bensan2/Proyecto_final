package es.uv.eu.buscarRaton.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
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
    // Variables para inicializar la configuracion (juego rapido)
    private Color color_inicial = Color.GREEN;
    private Color color_inicial2 = Color.WHITE;

    private JComboBox raton;
    private String[] ratones = {"Mickey", "Rasca", "Jerry","Examinar..."};
    
    private JComboBox asistente;
    private String[] asistente_respuestas = {"No", "Si"};
   
    JColorChooser ventana_colores;
    
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
        raton.setActionCommand("ListaRatonExaminar");
        this.add(raton);          
        
        
        lColor_celda = new JLabel("Color de celda:", SwingConstants.CENTER);
        this.add(lColor_celda);

        bColor_celda = new JButton("Pulse aqui");
        bColor_celda.setActionCommand("ColorCelda");
        color_celda = color_inicial;
        bColor_celda.setBackground(color_inicial);
        this.add(bColor_celda);

        
        lColor_fondo = new JLabel("Color de fondo:", SwingConstants.CENTER);
        this.add(lColor_fondo);
        
        bColor_fondo = new JButton("Pulse aqui");
        bColor_fondo.setActionCommand("ColorFondo");
        color_fondo = color_inicial2;
        bColor_fondo.setBackground(color_inicial2);
        this.add(bColor_fondo);

        
        bcerrar = new JButton("SALIR");
        bcerrar.setActionCommand("Salir");
        this.add(bcerrar);
        
        bempezar = new JButton("EMPEZAR");
        bempezar.setActionCommand("Empezar");
        this.add(bempezar);
 
        
        // Icono del Jframe y minimizado
        Image img = Toolkit.getDefaultToolkit().getImage("archivos/Icono.png");
        ImageIcon imgicon = new ImageIcon(img);
        this.setIconImage(img);
        
        // Fija el tamaño de la ventana
        setSize(300,300);
        // Visualiza la ventana
        setVisible(true);
        // No puede cambiar de tamaño
        setResizable(false);
        // Cerrar con el boton x de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Centra la ventana en la pantalla
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - 
                            getSize().width) / 2, 
                    (Toolkit.getDefaultToolkit().getScreenSize().height - 
                            getSize().height) / 2);
    }
       
    /**
    * Devuelve nombre de usuario del JTextField   
    * @return txtJugador.getText()
    */
    public String getNombre(){
        return txtJugador.getText();
    }
    
    /**
    * Devuelve raton elegido por el usuario   
    * @return raton.getSelectedItem().toString()
    */
    public String getRaton(){
        return raton.getSelectedItem().toString();
    }
    
    /**
    * Devuelve color de la celda asignada por el usuario
    * @return color_celda
    */
    public Color getColorCelda(){
        return color_celda;
    }
    
    /**
    * Devuelve color de fondo asignada por el usuario
    * @return color_fondo
    */
    public Color getColorFondo(){
        return color_fondo;
    }
    
    /**
    * Devuelve el numero del JTextField filas
    * @return Integer.parseInt(txtFilas.getText())
    */ 
     public int getFilas(){
        return Integer.parseInt(txtFilas.getText());
    }
    
    /**
    * Devuelve el numero del JTextField columnas
    * @return Integer.parseInt(txtColumnas.getText())
    */ 
    public int getColumnas(){
        return Integer.parseInt(txtColumnas.getText());
    }
    
    /**
    * Devuelve la cadena con la opcion elegida del combobox
    * @return aux_asistente
    */ 
    public boolean getAsistente(){
        boolean aux_asistente;
        aux_asistente = "Si".equals(asistente.getSelectedItem().toString());
        return aux_asistente;
    }
    
    
    /**
     * Asigna el color de la celda en configuracion
     */
    public void setColorCelda(){
        color_celda = ventana_colores.showDialog(null, "Seleccione un Color", Color.GREEN);
        bColor_celda.setBackground(color_celda);
    }
    
    /**
     * asigna el color de fondo en configuracion
     */
    public void setColorFondo(){
        color_fondo = ventana_colores.showDialog(null, "Seleccione un Color", Color.GRAY);
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
            JOptionPane.showMessageDialog(null,"Seleccione:" + "\n" +
                                               "Un color de celda y " +
                                               "un color de Fondo "   + 
                                               "Distintos" );
            datos_correctos = false;
        }
        return datos_correctos;
    }
            
    
    /**
    *  ACTION LISTENERS de configuracion
    * @param actionListener
    */ 
    public void setActionListener(ActionListener actionListener){
            bcerrar.addActionListener(actionListener);
            bempezar.addActionListener(actionListener);
            bColor_celda.addActionListener(actionListener);
            bColor_fondo.addActionListener(actionListener);
            raton.addActionListener(actionListener);
    }
}