package figurePack;

import java.awt.*;

public class Losangulo extends Figures {
    private int xPoints[], yPoints[];
    public Losangulo (int x, int y, int w, int h, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
        this.xPoints = new int[]{this.x, this.x + this.w / 2, this.x,  this.x - this.w / 2};
        this.yPoints = new int[]{this.y, this.y + this.h / 2, this.y + this.h, this.y + this.h/2};
    }
    public void print(){
        System.out.format("Losangulo de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(this.colorLine);
        g2d.drawPolygon(this.xPoints, this.yPoints, 4);
        g2d.setColor(this.colorBack);
        g2d.fillPolygon(this.xPoints, this.yPoints, 4);
    }
    public boolean contain (int x, int y){
        return (this.x<=x && x<=this.x+this.w && this.y<=y && y<=this.y+this.h);
    }
}
