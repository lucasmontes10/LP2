package figurePack;
import java.awt.*;

public class Superelipse extends Figures {
    public Superelipse (int x, int y, int w, int h, Color colorLine, Color colorBack) {
       super(x, y, w, h, colorLine, colorBack);
    }

    public void paint (Graphics g, boolean focused){
        Graphics2D g2d = (Graphics2D) g;
    
       	BasicStroke bs1 = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
	    g2d.setStroke(bs1);
    
        g2d.setColor( this.colorLine);
        g2d.drawRoundRect(this.x,this.y, this.w,this.h, 20, 20);
        
        g2d.setColor(this.colorBack);
        g2d.fillRoundRect(this.x, this.y, this.w, this.h, 20, 20);
        
        if(focused){
            int SIZE = 8;
            int x,y; int w,h;
            
            bs1 = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
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
        return (x>=this.x && x <= this.x + this.w && y >=this.y && y <=this.y+this.h);
    }
}
