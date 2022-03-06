package figures;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;

public class Ellipse 
{
    private int x, y;
    private int w, h;
    private Color colorLine, colorBack;

    public Ellipse (int x, int y, int w, int h, Color colorLine, Color colorBack){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorLine = colorLine;
        this.colorBack = colorBack;
    }

    public void print(){
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        //Aumentar a linha
        g2d.setStroke(new BasicStroke(7));
	
	    g2d.setColor(this.colorLine);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
	    g2d.setColor(this.colorBack);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w,this.h));
        
    }
}
