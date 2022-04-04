import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import figurePack.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;




//CheckList: 
//-Criar a figura na posição do mouse - ok
//Selecionar determinada figura - ok, parcialmente
//Fazendo a cor da figura: utilizaremos JColorChooser que está presente no pacote AWT
//Configurando o z-index


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
                    //Implementando o zindex no nosso projeto
                    int xAtual = (int) evt.getX();
                    int yAtual = (int) evt.getY();
                    //funcionalidade do z-index

                    for (int i = figs.size()-1; i>=0; i--){
                        Figures fig = figs.get(i);
                        //Preciso ver se o mouse esta no limite das figuras
                        if((xAtual >= fig.x && xAtual <= fig.x + fig.w) && (yAtual >= fig.y && yAtual <= fig.y + fig.h)){
                            //Estabelecendo a figura selecionada
                            focus = fig;
                            figs.remove(focus);
                            figs.add(focus);
                            break;
                        }else{
                            focus = null;
                            auxFig = null;
                        }
                    }

                    // if (focus == null){
                    //     ListIterator<Figures> item = figs.listIterator(figs.size());
                    //     Figures fig = null;
                    //     while (item.hasPrevious()){
                    //         fig = item.previous();
                    //         if((xAtual >= fig.x && xAtual <= fig.x + fig.w) && (yAtual >= fig.y && yAtual <= fig.y + fig.h)){
                    //             focus = fig;
                    //             int index = figs.indexOf(focus);
                    //             //Trocando de posição no array
                    //             figs.remove(index);
                    //             figs.add(focus);
                    //             break;
                    //         } else{
                    //             //Repetindo o do comentario
                    //             focus = null;
                    //             auxFig = null;
                    //         }
                    //     }
                    
                    repaint();
                }
            }
        );
        //Adicionando a funcionalidade de mover com o mouse e resize
        this.addMouseMotionListener(
            new MouseMotionAdapter(){
                public void mouseDragged(MouseEvent evt){
                    //iremos fazer o redisionamento
                    if(focus !=null){
                        focus.x = (int) evt.getX();
                        focus.y = (int) evt.getY();
                        repaint();
                    }
                }
            //     public void mouseDragged(MouseEvent evt){
			// 	    Point currentPt = evt.getPoint();

			//    	    if (pos == -1)
        	// 			return;
        	// 	    if( pos != 2){
            //             points[pos].x = currentPt.x; 
          	// 			points[pos].y = currentPt.y;

          	// 			int otherEnd = (pos==1)?2:1;


            //             double newPoint2X = points[otherEnd].getX() + (points[pos].getX() - points[otherEnd].getX())/2;
            //             double newPoint2Y = points[otherEnd].getY() + (points[pos].getY() - points[otherEnd].getY())/2;

            //             points[2].x = newPoint2X; 
            //             points[2].y = newPoint2Y;

			// 	   		focus.w = (int)Math.abs(points[1].getCenterX()-points[0].getCenterX());
			// 	   		focus.h = (int)Math.abs(points[1].getCenterY()-points[0].getCenterY());
        	// 		}else{
        	// 				//System.out.printf("Drag %d\n",pos);
            //             Double deltaX = currentPt.x - lastPoints[2].getX();
          	// 			Double deltaY = currentPt.y - lastPoints[2].getY();
            //             for(int j = 0; j < 3; j++){
			// 	   		 	points[j].x = lastPoints[j].getX() + deltaX;
			// 	   		 	points[j].y = lastPoints[j].getY() + deltaY;
			// 	   		}
			// 	   		focus.x = (int)points[0].getCenterX();
			// 	   		focus.y = (int)points[0].getCenterY();
        	// 		}

			//    	    repaint();
			//    	}
		 
			}  
        );

        this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt){
                    // int x = (int) localMouse.getClientX();
                    // int x = rand.nextInt(255);
                    // System.out.print(x);

                    // int y = (int) localMouse.getClientY();
                    // int y = rand.nextInt(255);
                    // Point mouseAtual = MouseInfo.getPointerInfo().getLocation();
                   
                    // int xAtual = (int) mouseAtual.getX();
                    // int yAtual = (int) mouseAtual.getY();
                    // MouseEvent mouse = null;
                    
                    // Point mouseAtual = mouse.getPoint();

                    Point p = MouseInfo.getPointerInfo().getLocation();
                    int xAtual = p.x - getLocation().x;
                    int yAtual = p.y - getLocation().y;

                    int w = defaultW;
                    int h = defaultH;
                    int r_line = rand.nextInt(255);
                    int g_line = rand.nextInt(255);
                    int b_line = rand.nextInt(255);
                    int r_back = rand.nextInt(255);
                    int g_back = rand.nextInt(255);
                    int b_back = rand.nextInt(255);
                    if( evt.getKeyChar() == 'r'){ 
                        figs.add( new Rect( xAtual, yAtual, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'e'){
                        figs.add( new Ellipse(xAtual, yAtual, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if( evt.getKeyChar() == 'l' ){
                        figs.add(new Losangulo(xAtual, yAtual, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        repaint();
                    }
                    if (evt.getKeyChar() == 't'){
                        figs.add(new Triangulo(xAtual, yAtual, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
                        repaint();
                    }
                    //Adicionando a função de excluir a figura em foco
                    if ((evt.getKeyCode() == KeyEvent.VK_DELETE)){
                        if (focus != null){
                            //Função de array list, justamente para excluir o elemento selecionado
                            figs.remove(focus);
                            focus = null;
                            repaint();
                        }
                    }

                    //Adicionando a função de mover as figuras com as setas

                    if (evt.getKeyCode() == KeyEvent.VK_UP){
                        if (focus != null){
                            //Modificando o y
                            focus.y -= 5;
                            repaint();
                        }
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_DOWN){
                        if (focus != null){
                            focus.y += 5;
                            repaint();
                        }
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_LEFT){
                        if (focus != null){
                            focus.x -= 5;
                            repaint();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_RIGHT){
                        if (focus != null){
                            focus.x += 5;
                            repaint();
                        }
                    }

                    if (evt.getKeyChar() == 'f'){
                        //Modificando a propriedade de cor de fundo
                        if (focus != null){
                            Color novaCor = JColorChooser.showDialog(null, "Escolha a cor", focus.colorBack);
                            focus.colorBack = novaCor;
                            repaint();
                        }
                    }

                    if (evt.getKeyChar() == 'b'){
                        //modificando a propriedade de cor de linha
                        if (focus != null){
                            Color novaCor = JColorChooser.showDialog(null, "Escolha a cor", focus.colorLine);
                            focus.colorLine = novaCor;
                            repaint();
                        }
                    }

                    if (evt.getKeyChar() == '+') {
                        if (focus != null) {
                            focus.h += 10;
                            focus.w += 10;
                            repaint();
                        }
                    }

                    if (evt.getKeyChar() == '-') {
                        if (focus != null) {
                            focus.h -= 10;
                            focus.w -= 10;
                            repaint();
                        }
                    }

                    if (evt.getKeyCode() == KeyEvent.VK_TAB){
                        if(focus != null){
                            int i;
                            if(figs.indexOf(focus) == figs.size() - 1){
                                i = 0;
                            }else{
                                i = figs.indexOf(focus) - 1;
                            }
                            focus = figs.get(i);
                        }
                        repaint();
                    }
			    }
		    }
			
		);


        this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Lista de Figuras");
        this.setSize(500, 500);
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
        //Desenhar os retangulos para resize

        // int x,y; 
        // int w,h;
		// x = focus.x; 
        // y = focus.y;
		// w = focus.w; 
        // h = focus.h;
		// points[0].x = (double)x-SIZE; points[0].y = (double)y-SIZE;
		// points[1].x = (double)x+w; points[1].y = (double)y+h; 
		// points[2].x = (double)((x+w)+(x-SIZE))/2; points[2].y = (double)((y+h)+y-SIZE)/2;
        // for (int i = 0; i < points.length; i++) {
        //     g2d.fill(points[i]);
        // }
    }
}