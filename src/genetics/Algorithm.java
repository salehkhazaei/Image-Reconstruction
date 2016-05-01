/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Saleh
 */
public class Algorithm {
    public static final int GENERATION_SIZE = 50 ;

    Random rand = new Random();
    Kromozom dna[] = new Kromozom[GENERATION_SIZE];
    BufferedImage target = null;
    Kromozom best = null;
    Kromozom worth = null;

    public void randomTriangle(BufferedImage buf, int tri_size ) {
        int w = buf.getWidth();
        int h = buf.getHeight();
        
        int x = rand.nextInt() % w ;
        int y = rand.nextInt() % h ;
        Triangle a = new Triangle();
        
        int cr = Math.abs(rand.nextInt() % 256);
        int cg = Math.abs(rand.nextInt() % 256);
        int cb = Math.abs(rand.nextInt() % 256);
        
        a.color = new Color(cr,cg,cb,100); 
        
        a.x[0] = rand.nextInt() % (w / 10) + x;
        a.x[1] = rand.nextInt() % (w / 10) + x;
        a.x[2] = rand.nextInt() % (w / 10) + x;

        a.y[0] = rand.nextInt() % (h / 10) + y;
        a.y[1] = rand.nextInt() % (h / 10) + y;
        a.y[2] = rand.nextInt() % (h / 10) + y;
        
        Graphics2D g = buf.createGraphics();
        g.setColor(a.color);
        g.fillPolygon(a.x, a.y, 3);
    }

    public void randomCircle(BufferedImage buf) {
        int w = buf.getWidth();
        int h = buf.getHeight();
        
        int cr = Math.abs(rand.nextInt() % 256);
        int cg = Math.abs(rand.nextInt() % 256);
        int cb = Math.abs(rand.nextInt() % 256);
        
        Color color = new Color(cr,cg,cb); //new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        int x = rand.nextInt() % w;
        int y = rand.nextInt() % h;
        int r = rand.nextInt() % (w / 3);

        Graphics2D g = buf.createGraphics();
        g.setColor(color);

        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
    public void makeFirstPopulation(BufferedImage target) {
        for ( int i = 0 ; i < GENERATION_SIZE ; i ++ )
        {
            dna[i] = new Kromozom(target);
        }
    }

    public Kromozom makeChild(BufferedImage target, Kromozom a, Kromozom b) {
        Kromozom c = new Kromozom(target);
        
        Graphics2D g = c.gen.createGraphics();
        
        BufferedImage ca = a.gen.getSubimage(0, 0, a.gen.getWidth() / 2, a.gen.getHeight());
        BufferedImage cb = b.gen.getSubimage(b.gen.getWidth() / 2, 0, b.gen.getWidth() / 2, b.gen.getHeight());
        
        g.drawImage(ca, 0, 0, c.gen.getWidth() / 2, c.gen.getHeight(), null);
        g.drawImage(cb, c.gen.getWidth() / 2, 0, c.gen.getWidth() / 2, c.gen.getHeight(), null);

        c.calcVal(target);
        return c;
    }
    public Kromozom makeChild2(BufferedImage buf, Kromozom a, Kromozom b) {
        Kromozom c = new Kromozom(target);
        
        Graphics2D g = c.gen.createGraphics();
        
        BufferedImage ca = a.gen.getSubimage(0, 0, a.gen.getWidth(), a.gen.getHeight() / 2);
        BufferedImage cb = b.gen.getSubimage(0, b.gen.getHeight() / 2, b.gen.getWidth() , b.gen.getHeight() / 2);
        
        g.drawImage(ca, 0, 0, c.gen.getWidth() , c.gen.getHeight() / 2, null);
        g.drawImage(cb, 0, c.gen.getHeight() / 2, c.gen.getWidth() , c.gen.getHeight() / 2, null);

        c.calcVal(target);
        return c;
    }

    public void makeChilds(BufferedImage target) 
    {
        for ( int i = 0 ; i < 10 ; i ++ )
        {
            dna[10 + i] = dna[i].copy(target);
            dna[20 + i] = dna[i].copy(target);
            dna[30 + i] = dna[i].copy(target);
            randomTriangle(dna[10 + i].gen,i+1);
            randomTriangle(dna[20 + i].gen,i+1);
            randomTriangle(dna[30 + i].gen,i+1);
            dna[10 + i].calcVal(target);
            dna[20 + i].calcVal(target);
            dna[30 + i].calcVal(target);
        }
        for ( int i = 0 ; i < 5 ; i ++ )
        {
            int a = rand.nextInt(10);
            int b = 0 ;
            while ( (b = rand.nextInt(10)) == a )
            {
            }
            dna[40 + i] = makeChild(target, dna[a], dna[b]);
        }
        for ( int i = 0 ; i < 5 ; i ++ )
        {
            int a = rand.nextInt(10);
            int b = 0 ;
            while ( (b = rand.nextInt(10)) == a )
            {
            }
            dna[45 + i] = makeChild2(target, dna[a], dna[b]);
        }
    }
    
    public void mutex ( BufferedImage target )
    {
        Kromozom a = new Kromozom(target);
        Graphics2D g = a.gen.createGraphics();
        int cr = Math.abs(rand.nextInt() % 256);
        int cg = Math.abs(rand.nextInt() % 256);
        int cb = Math.abs(rand.nextInt() % 256);
        
        Color c = new Color(cr,cg,cb);
        
        g.setColor(c);
        g.fillRect(0,0,a.gen.getWidth(),a.gen.getHeight());
        a.calcVal(target);
        dna[49] = a;
    }

    public void sort() {
        Collections.sort(Arrays.asList(dna),new Comparator<Kromozom>() {
            @Override
            public int compare(Kromozom o1, Kromozom o2) {
                if ( o1.value - o2.value == 0 )
                {
                    return 0 ;
                }
                else if ( o1.value - o2.value > 0 )
                {
                    return 1 ;
                }
                else
                {
                    return -1 ;
                }
            }
        });
    }

    public void run() {
        System.out.println(Color.white.getRGB() + " " + Color.black.getRGB());
        makeFirstPopulation(target);
        System.out.println("Population created");
        long iteration = 0 ;
        double mutex_prob = 0.1 ;
        while ( true )
        {
            boolean mut = false ;
            if ( rand.nextDouble() < mutex_prob )
            {
                mutex(target);
                mut = true ;
            }
            sort();
            best = dna[0];
            worth = dna[GENERATION_SIZE-1];
            System.out.println("calced {" + iteration + "}" + best.value + " " + worth.value + " " + mut );
            makeChilds(target);
            iteration ++ ;
        }
    }
}
