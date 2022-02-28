package exercicioProximo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import exercicioProximo.figures.*;

//Repositorio em relação ao exercício 2.3.2

public class FiguresPainted {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1, r2, r3;
    Ellipse e1, e2, e3;
    Losangulo l1, l2, l3;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(30,40, 100,30, new Color(117, 0, 0), new Color(117, 169, 0));
        this.r2 = new Rect(30,200, 100,30, new Color(0, 255, 0), new Color(255, 0, 0));
        this.r3 = new Rect(50, 150, 20, 40, new Color(152, 129, 9), new Color(152, 202, 236));
        this.e1 = new Ellipse(50,100, 100,30, new Color(90, 202, 236), new Color(195, 128, 236));
        this.e2 = new Ellipse(155, 150, 50,90, new Color(110, 0, 0), new Color(65, 169, 0) );
        this.e3 = new Ellipse(50, 250, 100, 65, new Color(119, 49, 70), new Color(241, 153, 70));
        //Documentacao: x, y, w, h
        this.l1 = new Losangulo(267, 260, 100, 60,  new Color(110, 0, 0), new Color(65, 169, 0));
        this.l2 = new Losangulo(250, 55, 110, 80, new Color(85, 183, 186), new Color(172, 183, 186));
        this.l3 = new Losangulo(250, 150, 70, 100, new Color(73, 48, 186), new Color(244, 230, 186));
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.l1.paint(g);
        this.l2.paint(g);
        this.l3.paint(g);
    }
}
