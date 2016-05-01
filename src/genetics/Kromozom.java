/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Saleh
 */
class Kromozom 
{
    public BufferedImage gen = null ;
    public long value = 0 ;

    Kromozom(BufferedImage target) {
        gen = new BufferedImage ( target.getWidth() , target.getHeight() , target.getType() );
        calcVal(target);
    }
    public String toString ( )
    {
        return "{" + value + " }";
    }
    public Kromozom copy (BufferedImage target) 
    {
        Kromozom z = new Kromozom(target);
        Graphics2D g = z.gen.createGraphics();
        g.drawImage(gen, null, 0, 0);
        z.value = value;
        return z ;
    }
    public void calcVal( BufferedImage target ) {
        long val = 0 ;
        for ( int i = 0 ; i < target.getWidth() ; i ++ )
        {
            for ( int j = 0 ; j < target.getHeight(); j ++ )
            {
                Color a = new Color(target.getRGB(i,j));
                Color b = new Color(gen.getRGB(i,j));
                val += Math.pow( a.getRed() - b.getRed() , 2.0 );
                val += Math.pow( a.getGreen() - b.getGreen() , 2.0 );
                val += Math.pow( a.getBlue() - b.getBlue() , 2.0 );
            }
        }
        this.value = val;
    }
}
