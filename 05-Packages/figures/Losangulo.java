package figures;
import java.awt.*;

public class Losangulo {
    int x, y;
    int w, h;
    Color back, line;

    public Losangulo (int x, int y, int w, int h, Color back, Color line) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.back = back;
        this.line = line;
    }

    public void print(){
        System.out.format("Losangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        int xPoints[] = {this.x, this.x + this.w / 2, this.x,  this.x - this.w / 2};
        int yPoints[] = {this.y, this.y + this.h / 2, this.y + this.h, this.y + this.h/2};
        g2d.setColor(this.back);
        g2d.fillPolygon(xPoints, yPoints, 4);
        g2d.setColor(this.line);
        g2d.drawPolygon(xPoints, yPoints, 4);
    }
}
