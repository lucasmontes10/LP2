package figurePack;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;

public class Ellipse extends Figures{
    private Ellipse (int x, int y, int w, int h, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
    }
    
    private void print(){
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        //Aumentar a linha
        g2d.setStroke(new BasicStroke(4));
	
	    g2d.setColor(this.colorLine);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
	    g2d.setColor(this.colorBack);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w,this.h));
        
        
    }

    public boolean clicked (int x, int y){
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
}