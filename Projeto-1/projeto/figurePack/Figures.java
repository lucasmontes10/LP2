package figurePack;

import java.awt.Graphics;
import java.awt.*;

import ivisible.IVisible;


public abstract class Figures implements IVisible {
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

    public abstract void print();
    public boolean clicked (int x, int y){
        return (x>=this.x && x <= this.x + this.w && y >=this.y && y <=this.y+this.h);
    }
}
