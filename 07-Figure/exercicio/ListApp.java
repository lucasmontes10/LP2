import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figuresPacote.*;

public class ListApp {
    public static void main(String[] args)
	{
		ListFrame frame = new ListFrame();
		frame.setVisible(true);
	}	
}

class ListFrame extends JFrame{
    ArrayList<Figures> figs = new ArrayList<Figures>();
    Random rand = new Random();
    
    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt)
				{
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
    }
}