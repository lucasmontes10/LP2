package figuresPack;
import java.awt.*;

public class Rect extends Figures{

    public Rect (int x, int y, int w, int h, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
    }

    public void print() {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n", this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        
        //consertar/ajustar bordas
        g2d.setStroke(new BasicStroke(7));
        
        g2d.setColor(this.colorLine);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        
        g2d.setColor(this.colorBack);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        
    }
}
