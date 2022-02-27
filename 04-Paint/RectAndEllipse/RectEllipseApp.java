package RectAndEllipse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import RectAndEllipse.figuresNew.*;


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
        this.r1 = new Rect(30,40, 100,30, new Color(0, 255, 0), new Color(255, 0, 0));
        this.r2 = new Rect(60, 80, 70, 20, new Color(117, 0, 0), new Color(117, 169, 0));
        this.r3 = new Rect(120, 150, 80, 70, new Color(80, 93, 195), new Color(3, 129, 9));
        this.r4 = new Rect(50, 150, 20, 40, new Color(152, 129, 9), new Color(152, 202, 236));
        //Criando novos exemplos
        this.e1 = new Ellipse(150,100, 100,30, new Color(110, 0, 0), new Color(65, 169, 0) );
        this.e2 = new Ellipse(220, 150, 40, 60, new Color(90, 202, 236), new Color(195, 128, 236));
        this.e3 = new Ellipse(190, 250, 135, 80, new Color(18, 244, 70), new Color(242, 244, 70));
        this.e4 = new Ellipse(50, 200, 70, 120, new Color(119, 49, 70), new Color(241, 153, 70));
    }

    public void paint (Graphics g) {
        super.paint(g);
        //Documentacao: g, back, line
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);

        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
        this.e4.paint(g);
    }
}
