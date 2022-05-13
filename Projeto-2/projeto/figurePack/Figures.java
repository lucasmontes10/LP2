package figurePack;

import java.awt.*;

import ivisible.IVisible;

import java.io.Serializable;

import java.awt.geom.Rectangle2D;

public abstract class Figures implements IVisible, Serializable {
    public int x, y;
    public int w, h;
    public Color colorLine, colorBack;
    protected Rectangle2D.Double[] pointsIfSelected = {new Rectangle2D.Double(50, 50, 8, 8), new Rectangle2D.Double(150, 100, 8, 8), new Rectangle2D.Double(100, 75, 8, 8)};

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

    public Rectangle2D.Double[] GetPointsOfSelection(){
    		return pointsIfSelected;
    }
}
