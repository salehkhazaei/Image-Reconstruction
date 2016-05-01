/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Saleh
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        this.setBounds(0,0,300,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(new MainPanel());
    }
    
    
    class MainPanel extends JPanel 
    {
        Algorithm algo = new Algorithm();
        public MainPanel() 
        {
            this.setBounds(0,0,256,160);
            try {
                algo.target = ImageIO.read(new File("minions.jpg"));
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Thread()
            {
                @Override
                public void run () 
                {
                    algo.run();
                }
            }.start();
            new Thread()
            {
                @Override
                public void run () 
                {
                    while(true)
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        MainPanel.this.repaint();
                    }
                }
            }.start();
        }

        
        @Override
        public void paint ( Graphics g2d )
        {
            Graphics2D g = (Graphics2D) g2d;
            if ( algo.best == null )
                return ;
            BufferedImage buf = algo.best.gen;
            g.drawImage(buf, null, 0,0);
        }   
    }
}
