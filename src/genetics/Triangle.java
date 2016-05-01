/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.awt.Color;

/**
 *
 * @author Saleh
 */
class Triangle
{
    int x[] = new int[3];
    int y[] = new int[3];
    Color color = null;
    public Triangle copy ()
    {
        Triangle c = new Triangle();
        for ( int i = 0 ; i < 3 ; i ++ )
        {
            c.x[i] = x[i];
            c.y[i] = y[i];
        }
        c.color = new Color( this.color.getRGB()) ;
        return c ;
    }
    public String toString()
    {
        return "{" + color.getRGB() + "," + x[0] + "," + x[1] + "," + x[2] + 
                                      "," + y[0] + "," + y[1] + "," + y[2] + "}";
    }

    public boolean equal(Triangle get) {
        if ( get.color.getRGB() != color.getRGB())
        {
            return false;
        }
        for ( int i = 0 ; i < 3 ; i ++ )
        {
            if ( x[i] != get.x[i] )
            {
                return false;
            }
            if ( y[i] != get.y[i] )
            {
                return false;
            }
        }
        return true;
    }
}
