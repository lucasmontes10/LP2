package figuresPacote;

import java.awt.*;
import java.awt.BasicStroke;

public class Triangulo extends Figures {
    private int x, y;
    private int w, h;
    private Color colorLine, colorBack;
    
    public Triangulo(int x,int y, int w, int h, Color colorLine, Color colorBack){
		this.x = x; 
		this.y = y;
		this.w = w;
		this.h = h;
		this.colorLine = colorLine;
		this.colorBack = colorBack;
	}
    public void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(4));
		int x[] = {this.x, this.x-w, this.x+w};
		int y[] = {this.y, this.y+w, this.y+w};
		
		g2d.setColor(this.colorLine);
		g2d.drawPolygon(x,y,3);
		
		g2d.setColor(this.colorBack);
		g2d.fillPolygon(x,y,3);
        }
}
