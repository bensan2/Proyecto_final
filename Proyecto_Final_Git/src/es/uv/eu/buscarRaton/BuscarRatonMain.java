package es.uv.eu.buscarRaton;
import es.uv.eu.buscarRaton.controller.BuscarRatonController;
import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import es.uv.eu.buscarRaton.view.Configuracion;
import es.uv.eu.buscarRaton.view.PanelCentralTablero;
/******************************************************************************
 * EU_Practica_ProyectoFinal
 * Utilizando el MVC se diseña un videojuego Rasca y Pica
 * 
 * main
 
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 *****************************************************************************/
public class BuscarRatonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BuscarRatonModel model = new BuscarRatonModel();
        Configuracion configuracion_view = new Configuracion(model);
        BuscarRatonController controller = new BuscarRatonController(model, configuracion_view);

        configuracion_view.setVisible(true);
    }
}
