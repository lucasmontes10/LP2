package figurePack;
import java.awt.*;
import java.awt.BasicStroke;

public class Ellipse extends Figures{
    public Ellipse (int x, int y, int w, int h, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
    }
    
    public void print(){
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D)g;
        
        //Aumentar a linha
        g2d.setStroke(new BasicStroke(4));
	
	    g2d.setColor(this.colorLine);
        g2d.drawOval(this.x, this.y, this.w, this.h);
	    g2d.setColor(this.colorBack);
        g2d.fillOval(this.x, this.y, this.w, this.h);

        if( focused ){
            int SIZE = 8;
            int x,y; int w,h;

            BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
            g2d.setStroke(bs1);



            x = this.x; 
            y = this.y;
            w = this.w;
            h = this.h;

            this.pointsIfSelected[0].x = (double)x-SIZE; 
            this.pointsIfSelected[0].y = (double)y-SIZE;

            this.pointsIfSelected[1].x = (double)x+w;
            this.pointsIfSelected[1].y = (double)y+h; 

            this.pointsIfSelected[2].x = (double)((x+w)+(x-SIZE))/2;
            this.pointsIfSelected[2].y = (double)((y+h)+y-SIZE)/2;
	    }   
    }

    public boolean clicked (int x, int y){
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
}