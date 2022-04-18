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
//Redimensionar a figura a selecionada - OK


public class App {
    public static void main(String[] args)
	{
		ListFrame frame = new ListFrame();
		frame.setVisible(true);
        frame.setFocusTraversalKeysEnabled(false);
	}	
}


class ListFrame extends JFrame{
    ArrayList<Figures> figs = new ArrayList<Figures>();
    Random rand = new Random();
    Figures focus = null;
    Figures auxFig = null;

    Point currentPoint = null;
    Point previousPoint = null;
    Point posMouse = null;
    
    int defaultH = 100;
	int defaultW = 100;
    int distX, distY;
    int moverFigX, moverFigY;

    int i = 0;

    boolean rectMiniFocus = false;
    Rect miniRect = new Rect (0, 0, 12, 12, Color.red, Color.white);
    
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
                    
                    //Pegando o posicionamento
                    posMouse = getMousePosition();

                    //funcionalidade do z-index

                    for (int i = figs.size()-1; i>=0; i--){
                        Figures fig = figs.get(i);
                        rectMiniFocus = false;
                        //Preciso ver se o mouse esta no limite das figuras
                        if(fig.clicked(xAtual, yAtual)){
                            //Estabelecendo a figura selecionada
                            focus = fig;
                            figs.remove(focus);
                            figs.add(focus);
                            //Calculo do mouse preparando para mover
                            moverFigX = xAtual - fig.x;
                            moverFigY = yAtual - fig.y;
                            break;
                        }else if(miniRect.clicked(xAtual, yAtual)){
                            rectMiniFocus = true;
                            distX = miniRect.x - xAtual;
                            distY = miniRect.y - yAtual;
                        }else{
                            focus = null;
                        }
                    }
                    
                    repaint();
                }
            }
        );
        //Adicionando a funcionalidade de mover com o mouse e resize
        this.addMouseMotionListener(
            new MouseMotionAdapter(){
                public void mouseDragged(MouseEvent evt){
                    Point novaPos = getMousePosition();
                    //iremos fazer o redisionamento

                    if(rectMiniFocus){
                        if (evt.getX() >= focus.x && evt.getY() >= focus.y){
                            figs.remove(focus);
                            focus.w += novaPos.x - posMouse.x;
                            focus.h += novaPos.y - posMouse.y;
                            figs.add(focus);
                            repaint();
                            posMouse.x = novaPos.x;
                            posMouse.y = novaPos.y;
                        }
                    }else{
                        if(focus !=null){
                            //Consertando o clique
                            focus.x = (int) evt.getX() - moverFigX;
                            focus.y = (int) evt.getY() - moverFigY;
                            repaint();
                        }
                    }
                }
			}  
        );

        this.addKeyListener(
			new KeyAdapter() {
				public void keyPressed(KeyEvent evt){

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
                        figs.add( new Rect( xAtual - w / 2, yAtual - h / 2, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        if (figs.size() > 1){
                            focus = figs.get(figs.size() - 1);
                            figs.remove(focus);
                            figs.add(focus);
                        }
                        repaint();
                    }
                    if( evt.getKeyChar() == 'e'){
                        figs.add( new Ellipse(xAtual- w / 2, yAtual - h / 2, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        if (figs.size() > 1){
                            focus = figs.get(figs.size() - 1);
                            figs.remove(focus);
                            figs.add(focus);
                        }
                        repaint();
                    }
                    if( evt.getKeyChar() == 'l' ){
                        figs.add(new Losangulo(xAtual - w / 2, yAtual - h / 2, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        if (figs.size() > 1){
                            focus = figs.get(figs.size() - 1);
                            figs.remove(focus);
                            figs.add(focus);
                        }                        
                        repaint();
                    }
                    if (evt.getKeyChar() == 't'){
                        figs.add(new Triangulo(xAtual- w / 2, yAtual - h / 2, w, h, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
                        if (figs.size() > 1){
                            focus = figs.get(figs.size() - 1);
                            figs.remove(focus);
                            figs.add(focus);
                        }
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
                            // int i;
                            // if(figs.indexOf(focus) + 1 == figs.size() - 1){
                            //     i = 0;
                            // }else{
                            //     i = figs.indexOf(focus) - 1;
                            // }
                            // focus = figs.get(i);
                            if (figs.size() > 0){
                                focus = figs.get(i);
                                i++;
                                figs.remove(focus);
                                figs.add(focus);
                            }
                            if (i >= figs.size()){
                                i = 0;
                            }
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
            miniRect.x = (focus.x - 4) + (focus.w + 10) - 8;
            miniRect.y = (focus.y - 5) + (focus.h + 10) - 8;
            miniRect.paint(g);
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