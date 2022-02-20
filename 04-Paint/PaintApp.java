import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    //Criando outros exemplos
    Rect r1, r2, r3, r4;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.setVisible(true);
        this.r1 = new Rect(30,40, 100,30);
        this.r2 = new Rect(60, 80, 70, 20);
        this.r3 = new Rect(120, 150, 80, 70);
        this.r4 = new Rect(50, 150, 20, 40);
    }

    public void paint (Graphics g) {
        super.paint(g);
        //Documentacao: g, back, line
        this.r1.paint(g, new Color(0, 255, 0), new Color(255, 0, 0));
        this.r2.paint(g, new Color(117, 0, 0), new Color(117, 169, 0));
        this.r3.paint(g, new Color(80, 93, 195), new Color(3, 129, 9));
        this.r4.paint(g, new Color(152, 129, 9), new Color(152, 202, 236));
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
