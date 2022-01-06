package es.uv.eu.buscarRaton.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JWindow;

public class ImagenInicial extends JWindow{

    Image img = Toolkit.getDefaultToolkit().getImage("archivos/Juego.png");
    ImageIcon imgicon = new ImageIcon(img);    
   
    public ImagenInicial() throws InterruptedException {
        setSize(500,600);
        setLocationRelativeTo(null);
        setVisible(true);
        Thread.sleep(2000);
        dispose();
    }

    public void paint(Graphics g){
        g.drawImage(img,0,0,this);
    }

}
