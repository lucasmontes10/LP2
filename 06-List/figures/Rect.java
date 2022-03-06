package figures;

import java.awt.*;

public class Rect{
    private int x, y;
    private int w, h;
    private Color colorLine, colorBack;

    public Rect (int x, int y, int w, int h, Color colorLine, Color colorBack) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorLine = colorLine;
        this.colorBack = colorBack;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
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