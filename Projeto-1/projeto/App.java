import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.Random;
import figurePack.*;

public class App {
    public static void main(String[] args)
	{
		ListFrame frame = new ListFrame();
		frame.setVisible(true);
	}	
}

class ListFrame extends JFrame{
    ArrayList<Figures> figs = new ArrayList<Figures>();
    Random rand = new Random();
    Figures focus = null;
    //Pegando a posição do mouse
    Point localMouse;
    Point supMouse;
    
    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.addMouseListener(
            new MouseAdapter(){
                public void mousePressed(MouseEvent evt){
                    focus = null;
                    for (Figures fig: figs){
                        //Preciso ver se o mouse esta no limite das figuras
                        if((evt.getClientX() >= fig.x && evt.getClientX() <= fig.x + fig.w) && (evt.getClientY() <= fig.y && evt.getClientY() <= fig.y + fig.h)){
                            focus = fig;
                        }
                    }
                }
            }
        );


        this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt)
				{
                    // int x = (int) localMouse.getX();
                    int x = rand.nextInt(255);

                    // int y = (int) localMouse.getY();
                    int y = rand.nextInt(255);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    int r_line = rand.nextInt(255);
                    int g_line = rand.nextInt(255);
                    int b_line = rand.nextInt(255);
                    int r_back = rand.nextInt(255);
                    int g_back = rand.nextInt(255);
                    int b_back = rand.nextInt(255);
                    if( evt.getKeyChar() == 'r'){ 
                        figs.add( new Rect(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'e'){
                        figs.add( new Ellipse(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'l' ){
                        figs.add(new Losangulo(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if (evt.getKeyChar() == 't'){
                        figs.add(new Triangulo(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
                        repaint();
                    }
			    }
		    }
			
		);
        this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Lista de Figuras");
        this.setSize(1500, 1500);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figures fig: this.figs) {
            fig.paint(g);
        }
        if(focus != null){
            desenharRectSuporte(g);
        }
    }
    public void desenharRectSuporte(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(new Color(255, 0, 0));
        //Desenhar o retangulo em volta da figura selecionada
        g2d.drawRect(focus.x - 5, focus.y - 5, focus.w + 10, focus.h + 10);
    }
}