package RectAndEllipse;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

class RectEllipseApp {
    public static void main (String[] args) {
        RectEllipseFrame frame = new RectEllipseFrame();
        frame.setVisible(true);
    }
}

class RectEllipseFrame extends JFrame {
    Rect r1, r2, r3, r4;
    Ellipse e1, e2, e3, e4;

    RectEllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Rect + Ellipse");
        this.setSize(350, 350);
        this.r1 = new Rect(30,40, 100,30);
        this.r2 = new Rect(60, 80, 70, 20);
        this.r3 = new Rect(120, 150, 80, 70);
        this.r4 = new Rect(50, 150, 20, 40);
        this.e1 = new Ellipse(150,100, 100,30);
        //Criando novos exemplos
        this.e2 = new Ellipse(220, 150, 40, 60);
        this.e3 = new Ellipse(190, 250, 135, 80);
        this.e4 = new Ellipse(50, 200, 70, 120);
    }

    public void paint (Graphics g) {
        super.paint(g);
        //Documentacao: g, back, line
        this.r1.paint(g, new Color(0, 255, 0), new Color(255, 0, 0));
        this.r2.paint(g, new Color(117, 0, 0), new Color(117, 169, 0));
        this.r3.paint(g, new Color(80, 93, 195), new Color(3, 129, 9));
        this.r4.paint(g, new Color(152, 129, 9), new Color(152, 202, 236));

        this.e1.paint(g, new Color(90, 202, 236), new Color(195, 128, 236));
        this.e2.paint(g, new Color(18, 244, 70), new Color(242, 244, 70));
        this.e3.paint(g, new Color(119, 49, 70), new Color(241, 153, 70));
        this.e4.paint(g, new Color(111, 153, 70), new Color(227, 47, 70));
    }
}

class Rect {
    int x, y;
    int w, h;
    Color back, line;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

	void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g, Color back, Color line) {
        Graphics2D g2d = (Graphics2D) g;
        this.back = back;
        this.line = line;
        g2d.setStroke(new BasicStroke(7));
        g2d.setColor(this.line);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.back);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    Color back, line;

    Ellipse (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g, Color back, Color line) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(7));
        this.back = back;
        this.line = line;
        g2d.setColor(this.line);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.back);
        g2d.fillOval(this.x, this.y, this.w, this.h);
    }
}
