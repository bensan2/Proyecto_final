package es.uv.eu.buscarRaton;

import es.uv.eu.buscarRaton.controller.BuscarRatonController;
import es.uv.eu.buscarRaton.model.BuscarRatonModel;
import es.uv.eu.buscarRaton.view.Configuracion;
import es.uv.eu.buscarRaton.view.ImagenInicial;

/******************************************************************************
 * EU_Practica_ProyectoFinal
 * Utilizando el MVC se diseña un videojuego llamado Rasca&Pica.
 * El objetivo del videojuego es encontrar al raton dentro de una matriz sin
 * que los puntos llegen a 0 pts.
 * 
 * Nota:
 *    Ya que nuestro codigo no acepta "&" utilizaremos el nombre de BuscarRaton
 * 
 * main
 * 
 * GITHUB publico para acceso al profesor
 * https://github.com/bensan2/Proyecto_final
 * 
 * 
 * @author Kevin Daniel Baguian Nsue
 * @author Benjamin Sanchez Monreal
 * @version 1.0 2021/12/09
 *****************************************************************************/
public class BuscarRatonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        // Muestra la imagen inicial del videojuego
        ImagenInicial imagen_inicial = new ImagenInicial();
        
        // Inicializamos el modelo
        BuscarRatonModel model = new BuscarRatonModel();
        // Inicializamos la vista de configuracion
        Configuracion configuracion = new Configuracion();
        // Inicializamos el controlador 
        BuscarRatonController controller = 
                                new BuscarRatonController(model, configuracion);

        configuracion.setVisible(true);
    }
}
