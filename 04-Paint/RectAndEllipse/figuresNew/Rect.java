package RectAndEllipse.figuresNew;
import java.awt.*;

public class Rect {
    int x, y;
    int w, h;
    Color back, line;

    public Rect (int x, int y, int w, int h, Color back, Color line) {
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
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.back);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
