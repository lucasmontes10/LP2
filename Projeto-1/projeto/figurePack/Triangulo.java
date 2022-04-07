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
		g2d.setStroke(new BasicStroke(4));
		int[] xFill = {this.x+this.w, (2*this.x+this.w)/2, this.x};
        int[] yFill = {this.y+this.h, this.y, this.y+this.h};
        Polygon triangle = new Polygon(xFill, yFill, 3);
		
        g2d.setColor(this.colorLine);

        int[] lxStroke = {(2 * this.x+this.w)/2, this.x};
        int[] lyStroke = {this.y, this.y+this.h};
        int[] RxStroke = {(2 * this.x+this.w)/2, (this.x+this.w)};
        int[] RyStroke = {this.y, this.y+this.h};
        int[] BxStroke = {this.x+this.w, this.x};
        int[] ByStroke = {this.y+this.h, this.y+this.h};
        g.drawPolygon(lxStroke, lyStroke, 2);
        g.drawPolygon(RxStroke, RyStroke, 2);
        g.drawPolygon(BxStroke, ByStroke, 2);

		
		g2d.setColor(this.colorBack);

        g.fillPolygon(triangle);

        
	}

    public boolean contain (int x, int y){
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
}