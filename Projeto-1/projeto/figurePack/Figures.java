package figurePack;

import java.awt.Graphics;
import java.awt.*;


public abstract class Figures {
    public int x, y;
    public int w, h;
    public Color colorLine, colorBack;

    public Figures(int x, int y, int w, int h, Color colorLine, Color colorBack) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.colorLine = colorLine;
        this.colorBack = colorBack;
    }

    public abstract void paint (Graphics g);
    public abstract void print();

    public void redimensionar(int tx, int ty){
        this.w = tx - this.x;
        this.h = ty - this.y;
    }

    public boolean focusToRize (int x, int y) {
        return(x == this.x + this.w && y == this.y + this.h);
    }

    public void drag (int tx, int ty, int xf, int yf) {
        this.x = (tx - xf);
        this.y = (ty - yf);
    }

}
