import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        // aumento a linha
        g2d.setStroke(new BasicStroke(2));
        int w = getWidth();
        g2d.setPaint(Color.green);
        g2d.fillRect(0, 300, w, 50);
        g2d.setPaint(Color.black);
        g2d.fillRect(0, 200, w, 100);
        g2d.setPaint(Color.green);
        g2d.fillRect(0, 150, w, 50);
        g2d.setColor(Color.white);
        int controlador = 10;
        g2d.setColor(Color.white);
        //colocando a coloração da rua com partes em branco
        for(int i = 0; i < 10; i++){
            g2d.fillRect(controlador, 250, 30, 20);
            controlador += 70;
        }
        g2d.setColor(Color.cyan);
        g2d.fillRect(0, 0, w, 150);
        //Fazendo o desenho do sol
        g2d.setColor(Color.yellow);
        g2d.fillOval(250, 60, 60, 60);
        g2d.drawLine(280, 60, 280, 40);
        g2d.drawLine(280, 60, 280, 140);
        g2d.drawLine(280, 90, 335, 90);
        g2d.drawLine(280, 90, 225, 90);
        g2d.drawLine(280, 90, 240, 125);
        g2d.drawLine(280, 90, 320, 55);
        g2d.drawLine(280, 90, 320, 125);
        g2d.drawLine(280, 90, 240, 55);
        //desenhando o bonequinho de palito
        g2d.setColor(Color.black);
        g2d.drawOval(65, 100, 30, 30);
        g2d.drawLine(80, 130, 80, 170);
        g2d.drawLine(80, 130, 65, 160);
        g2d.drawLine(80, 130, 95, 160);
        g2d.drawLine(80, 170, 70, 185);
        g2d.drawLine(80, 170, 90, 185);

        // g2d.setPaint(Color.red);
        // //Pintando a cor da linha
        // g2d.drawRect(0, 0, 100, 150);
        // //desenhando escrever um retangulo
        // g2d.fillRect(20, 20, 100, 150);
        // //desenhando um retangulo pintado
        // g2d.drawOval(0, 0, 100, 150);
        // //desenhando um circulo sem ser pintado
        int[] xValues = { 200, 250, 225};
        int[] yValues = { 165, 165, 140};
        Polygon poligono = new Polygon(xValues, yValues, 3);
        g2d.setColor(Color.yellow);
        g2d.fillPolygon(poligono);
        // g2d.drawLine(0,0, w,h);
        // g2d.drawLine(0,h, w,0);
    }
}
