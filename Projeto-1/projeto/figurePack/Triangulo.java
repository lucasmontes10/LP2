package figurePack;

import java.awt.*;
import java.awt.BasicStroke;

public class Triangulo extends Figures{
    public Triangulo(int x, int y, int w, int h, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
    }

    public void print(){
        System.out.format("Triangulo de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2));
		int x[] = {this.x, this.x-w, this.x+w};
		int y[] = {this.y, this.y+w, this.y+w};
		
		g2d.setColor(this.colorLine);
		g2d.drawPolygon(x,y,3);
		
		g2d.setColor(this.colorBack);
		g2d.fillPolygon(x,y,3);
	}
}