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

}
