package es.uv.eu.buscarRaton.controller;

import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import es.uv.eu.buscarRaton.view.Configuracion;
import es.uv.eu.buscarRaton.view.Juego;
import es.uv.eu.buscarRaton.view.PanelCentralTablero;
import es.uv.eu.buscarRaton.view.Ranking;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 */
public class BuscarRatonController {
    
    private BuscarRatonModel model;
    private Configuracion configuracion;
    private Juego juego;
    private Ranking ranking;
    
    private int continuar;
    
    public BuscarRatonController(BuscarRatonModel model, Configuracion configuracion){
        
        this.model = model;
        this.configuracion = configuracion;
        
        juego = new Juego(model);
        ranking = new Ranking();
        
        configuracion.addWindowListener(new BuscarRatonControllerWindowListener());
        configuracion.setActionListener(new BuscarRatonControllerActionListener());
        juego.addWindowListener(new BuscarRatonControllerWindowListener());
        juego.setActionListener(new BuscarRatonControllerActionListener());
        ranking.addWindowListener(new BuscarRatonControllerWindowListener());
        ranking.setActionListener(new BuscarRatonControllerActionListener());
        
    }
    
    /**
     * Clases empotradas
     */
    class BuscarRatonControllerWindowListener extends WindowAdapter{
            @Override
        public void windowClosing(WindowEvent e) 
        {
            System.out.println(" BuscarRatonController : Cerrar ventana.");
            System.exit(0);
        }
    }
    
    /**
    * Gestionar los ActionPerformed de los ActionListener
    */
    class BuscarRatonControllerActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent ae){
            String command = ae.getActionCommand();
             switch (command){
                case "buttonSalir": 
                    System.out.println("BuscarRatonController : Boton salir.");
                    continuar = JOptionPane.showConfirmDialog(null,
                            "¿Estas seguro?." + "\n" +
                            " Se borrara el progreso actual.",
                    "Seleccione la opcion correcta",JOptionPane.YES_NO_OPTION);
                    if (continuar == 0){
                        System.exit(0);
                    }

                break;
                
                // Juego nuevo
                case "buttonEmpezar":
                    System.out.println("BuscarRatonController: Boton Empezar");
                    if (configuracion.DatosCorrectos() == true){
                        model.JuegoNuevo(configuracion.getNombre(), 
                        configuracion.getRaton(), 
                        configuracion.getColorCelda(),
                        configuracion.getColorFondo(), 
                        configuracion.getAsistente(), 
                        configuracion.getFilas(),
                        configuracion.getColumnas());

                        // Cerrar ventana y abrir nueva ventana de juego 
                        configuracion.dispose();
                        juego.Reset(model.getNombreJugador(),
                                    model.getPuntos(),
                                    model.getAsistente(),
                                    model.getColorCelda());
                        juego.setVisible(true);
                        System.out.println("Datos correctos");
                    }
                break;
                //Salir una vez iniciado el juego
                case "Salir": 
                    System.out.println("BuscarRatonController : Boton salir.");
                    continuar = JOptionPane.showConfirmDialog(null,
                            "¿Estas seguro?." + "\n" +
                            " Se borrara el progreso actual.",
                    "Seleccione la opcion correcta",JOptionPane.YES_NO_OPTION);
                    if (continuar == 0){
                        System.exit(0);
                    }

                break;
                
                // Asistente
                case "Activar_asistente":
                    model.setAsistente(true);
                    System.out.println("Asistente activado");
                break;
                case "Desactivar_asistente":
                    model.setAsistente(false);
                    System.out.println("Asistente desactivado");
                break;
                
                // MENU BAR
                //Configuracion
                case "ItemNueva_configuacion":
                    continuar = JOptionPane.showConfirmDialog(null,
                            "¿Estas seguro?." + "\n" +
                            " Se borrara el progreso actual.",
                    "Seleccione la opcion correcta",JOptionPane.YES_NO_OPTION);
                    if (continuar == 0){
                        // abrir ventana de configuracion
                        juego.dispose();
                        configuracion.setVisible(true);
                    }
                    System.out.println("Nueva_configuacion");
                break;    
                
                case "ItemResetear":
                    continuar = JOptionPane.showConfirmDialog(null,
                            "¿Estas seguro?." + "\n" +
                            " Se borrara el progreso actual.",
                    "Seleccione la opcion correcta",JOptionPane.YES_NO_OPTION);
                    if (continuar == 0){
                            model.Reset();
                            juego.Reset(model.getNombreJugador(),
                                        model.getPuntos(),
                                        model.getAsistente(),
                                        model.getColorCelda());
                    }
                break;
                
                //Ranking
                case "ItemRanking":
                    System.out.println( " BuscarRatonController : Menu ’Ranking ’. " );
                    ranking.setVisible(true);
                break;
                
                //Accesibilidad
                case "ItemLupa":
                    try {
                        String cmd = new String();
                        cmd = "cmd /c c:/windows/system32/magnify.exe"; ///< La /c Indica -> Carries out the command specified by string and then terminates.
                        Runtime rt = Runtime.getRuntime();
                        rt.exec(cmd);
                        } catch (Exception e) {e.printStackTrace();}
                break;
                
                //Ayuda
                case "ItemManual":
                        try {
                            File path = new File ("archivos/manual.pdf");
                            Desktop.getDesktop().open(path);
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                break;
                
                case "ItemAcercaDe":
                        JOptionPane.showMessageDialog(null, 
                        "Programadores:" + "\n" +
                        "   Benjamin Sanchez Monreal" + "\n" +
                        "   Kevin Daniel Baguian Nsue" + "\n") ;
                break;
                
                // Tablero panel central
                case "Matriz":
                    model.DescontarPuntos();
                    JButton identif1 = (JButton) ae.getSource();
                    String s1 = (String)identif1.getName();
                    juego.repaintJuego(model.getPuntos(),s1,model.getColorFondo());
                    
                    // GAME OVER
                    if (model.getPuntos() <= 0){
                            continuar = JOptionPane.showConfirmDialog(null,
                            "GAME OVER" + "\n" +
                            "¿Desea volver a intentarlo?",
                            "Seleccione la opcion que desee", 
                            JOptionPane.YES_NO_OPTION);
                            // Si desea volver a jugar
                            if (continuar == 0){
                                model.Reset();
                                juego.Reset(model.getNombreJugador(),
                                            model.getPuntos(),
                                            model.getAsistente(),
                                            model.getColorCelda());
                            }
                            else{
                                System.exit(0);
                            }
                    }
                    System.out.println("BuscarRatonController : Boton Matriz.");
                break;
             }
        }
    }
}
