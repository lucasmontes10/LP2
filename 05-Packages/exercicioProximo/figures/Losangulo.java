package exercicioProximo.figures;

import java.awt.*;

public class Losangulo {
    private int x, y;
    private int w, h;
    private Color back, line;
    private int xPoints[], yPoints[];

    public Losangulo (int x, int y, int w, int h, Color back, Color line) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.back = back;
        this.line = line;
        this.xPoints = new int[]{this.x, this.x + this.w / 2, this.x,  this.x - this.w / 2};
        this.yPoints = new int[]{this.y, this.y + this.h / 2, this.y + this.h, this.y + this.h/2};
    }

    public void print(){
        System.out.format("Losangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(this.back);
        g2d.fillPolygon(this.xPoints, this.yPoints, 4);
        g2d.setColor(this.line);
        g2d.drawPolygon(this.xPoints, this.yPoints, 4);
    }
}