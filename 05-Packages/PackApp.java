import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;
import figures.Rect; //Por algum motivo o vscode tava considerando a classe de Rect de
//outra pasta, mais precisamente 03-Rect, ao fazer a importação individual consegui resolver

//Foi adicionado o Losangulo
class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1;
    Ellipse e1;
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
        this.r1 = new Rect(30,40, 100,30);
        this.e1 = new Ellipse(50,100, 100,30, new Color(90, 202, 236), new Color(195, 128, 236));
        //Documentacao: x, y, w, h
        this.l1 = new Losangulo(100, 170, 100, 60);
        this.l2 = new Losangulo(250, 55, 110, 80);
        this.l3 = new Losangulo(250, 150, 70, 100);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
        this.l1.paint(g);
        this.l2.paint(g);
        this.l3.paint(g);
    }
}