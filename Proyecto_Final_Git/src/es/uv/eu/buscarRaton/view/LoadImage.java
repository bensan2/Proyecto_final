package es.uv.eu.buscarRaton.view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Reutilizado de la practica 4
 * @author Profesor
 * @version 1.0 2021/12/09
 */
public class LoadImage extends JFileChooser {
    
    private static final String[] EXTENSIONES = { "jpg", "jpeg", "png", "gif",
        "tiff", "JPG", "JPEG" };
    
    public LoadImage() {
        super();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Imágenes", EXTENSIONES);
        this.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.setDialogType(JFileChooser.OPEN_DIALOG);
        this.setFileFilter(extensionFilter);
        this.setVisible(true);
    }
  
    public File getFile() {
        int ret = showDialog(null, "Abrir imagen");

        if (ret == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        }
        else {
            return null;
        }
    }
}