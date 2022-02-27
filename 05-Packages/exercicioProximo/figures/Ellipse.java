package exercicioProximo.figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse {
    private int x, y;
    private int w, h;
    private Color back;
    private Color line;

    public Ellipse (int x, int y, int w, int h, Color back, Color line) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.back = back;
        this.line = line;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(7));
        g2d.setColor(this.line);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.back);
        g2d.fillOval(this.x, this.y, this.w, this.h);
    }
}
