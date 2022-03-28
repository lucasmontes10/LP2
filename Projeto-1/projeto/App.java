import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;



import java.util.ArrayList;
import java.util.Random;
import figurePack.*;

//CheckList: 
//-Criar a figura na posição do mouse - ok
//Selecionar determinada figura - Fazendo

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
    Figures auxFig = null;

    Point currentPoint = null;
    Point previousPoint = null;

    int defaultH = 100;
	int defaultW = 100;
    
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
                    int xAtual = (int) evt.getX();
                    int yAtual = (int) evt.getY();
                    focus = null;
                    for (Figures fig: figs){
                        //Preciso ver se o mouse esta no limite das figuras
                        if((xAtual >= fig.x && xAtual <= fig.x + fig.w) && (yAtual >= fig.y && yAtual <= fig.y + fig.h)){
                            System.out.print("entrei aqui");
                            //Estabelecendo a figura selecionada
                            focus = fig;
                            break;
                        }else{
                            focus = null;
                            auxFig = null;
                        }
                    }
                    repaint();
                }
                public void mouseReleased(MouseEvent evt){

                }
            }
        );


        this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt)
				{
                    // int x = (int) localMouse.getClientX();
                    // int x = rand.nextInt(255);
                    // System.out.print(x);

                    // int y = (int) localMouse.getClientY();
                    // int y = rand.nextInt(255);
                    Point mouseAtual = MouseInfo.getPointerInfo().getLocation();

                    int w = defaultW;
                    int h = defaultH;
                    int r_line = rand.nextInt(255);
                    int g_line = rand.nextInt(255);
                    int b_line = rand.nextInt(255);
                    int r_back = rand.nextInt(255);
                    int g_back = rand.nextInt(255);
                    int b_back = rand.nextInt(255);
                    if( evt.getKeyChar() == 'r'){ 
                        figs.add( new Rect((int) mouseAtual.getX(), (int) mouseAtual.getY(), w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'e'){
                        figs.add( new Ellipse((int) mouseAtual.getX(), (int) mouseAtual.getY(), w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'l' ){
                        figs.add(new Losangulo((int) mouseAtual.getX(), (int) mouseAtual.getY(), w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if (evt.getKeyChar() == 't'){
                        figs.add(new Triangulo((int) mouseAtual.getX(), (int) mouseAtual.getY(), w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
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
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.red);
        //Desenhar o retangulo em volta da figura selecionada
        g2d.drawRect(focus.x - 5, focus.y - 5, focus.w + 10, focus.h + 10);
    }
}