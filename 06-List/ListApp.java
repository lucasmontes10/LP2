import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp
{
	public static void main(String[] args)
	{
		ListFrame frame = new ListFrame();
		frame.setVisible(true);
	}	
}

class ListFrame extends JFrame{
	ArrayList<Rect> rs = new ArrayList<Rect>();
	ArrayList<Ellipse> es = new ArrayList<Ellipse>();
    	ArrayList<Losangulo> lo = new ArrayList<Losangulo>();
	Random rand = new Random();
	
	ListFrame() {
		this.addWindowListener (
		    new WindowAdapter() {
		        public void windowClosing (WindowEvent e) {
		            System.exit(0);
		        }
		    }
		);
		
		this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt){
				int x = rand.nextInt(350);
				int y = rand.nextInt(350);
				int w = rand.nextInt(50);
				int h = rand.nextInt(50);
                int r_line = rand.nextInt(255);
                int g_line = rand.nextInt(255);
                int b_line = rand.nextInt(255);
                int r_back = rand.nextInt(255);
                int g_back = rand.nextInt(255);
                int b_back = rand.nextInt(255);
                //Evento para o retangulo
				if( evt.getKeyChar() == 'r'){
					rs.add( new Rect(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
					repaint();
                }
                //evento para Ellipse
				if( evt.getKeyChar() == 'e'){
					es.add( new Ellipse(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
					repaint();
				}
                //evento para Losangulo
                if( evt.getKeyChar() == 'l'){
					lo.add( new Losangulo(x, y, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
					repaint();
				}
			}
		}
			
		);
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Lista de Figuras");
        	this.setSize(1500, 1500);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		for( Rect r: this.rs){
			r.paint(g);
		}
		for( Ellipse e: this.es){
			e.paint(g);
		}
        for( Losangulo l: this.lo){
            l.paint(g);
        }
	}
}
