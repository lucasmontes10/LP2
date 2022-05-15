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

import java.io.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import button_.*;
import toolbox.*;


//Avanços:
//Salvando em arquivo
//Ajustando a toolbar e os botões


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
    ArrayList<Button_> buttons = new ArrayList<Button_>();

	Toolbox mainToolbox;
    Random rand = new Random();
    Figures focus = null;
    Figures auxFig = null;

    Point currentPoint = null;
    Point previousPoint = null;
    Point posMouse = null;
    int SIZE = 8;
    
    int defaultH = 100;
	int defaultW = 100;
    int distX, distY;
    int moverFigX, moverFigY;
    int pos = -1;
    Point prevPt = null;
    Point2D[] lastPoints = new Point2D[3];
	Rectangle2D.Double[] points = {	new Rectangle2D.Double(50, 50, SIZE, SIZE),  new Rectangle2D.Double(150, 100, SIZE, SIZE), new Rectangle2D.Double(100, 75, SIZE, SIZE) };
    int i = 0;

    boolean rectMiniFocus = false;
    Rect miniRect = new Rect (0, 0, 12, 12, Color.red, Color.white);


    int buttonId = -1;
    
    ListFrame () {

        try{
			FileInputStream f = new FileInputStream("proj.bin");
			ObjectInputStream o = new ObjectInputStream(f);

			this.figs = (ArrayList<Figures>)o.readObject();
			o.close();

		}catch(Exception x){
			System.out.println("ERRO! ao abrir arquivo");
			System.out.println(x.getMessage());
		}

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent) {
                    try {
                        FileOutputStream file = new FileOutputStream("proj.bin");
                        ObjectOutputStream object = new ObjectOutputStream(file);
                        object.writeObject(figs);
                        object.flush();
                        object.close();
                    }catch (Exception e) {
                        System.out.print(e);
                        System.out.println(e.getMessage());
                }
                System.exit(0);
            }
        });

        buttons.add(new Button_(1, new Rect(1, 2, 3, 4, Color.BLACK, Color.BLACK)) );
        buttons.add(new Button_(2, new Superelipse(1, 2, 3, 4, Color.BLACK, Color.BLACK)));
		buttons.add(new Button_(3, new Ellipse(1, 2, 3, 4, Color.BLACK, Color.BLACK)) );
		buttons.add(new Button_(4, new Triangulo(1, 2, 3, -1, Color.BLACK, Color.BLACK)) );
					      
		mainToolbox = new Toolbox(10, 50, buttons);


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
                    int r_line = rand.nextInt(255);
                    int g_line = rand.nextInt(255);
                    int b_line = rand.nextInt(255);
                    int r_back = rand.nextInt(255);
                    int g_back = rand.nextInt(255);
                    int b_back = rand.nextInt(255);
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

                    for(Button_ bt: buttons){
                        if( bt.clicked( xAtual, yAtual)){
                            buttonId = bt.id;
                            repaint();
                            return;
                        }
                    }

                    if( buttonId != -1){
                        if(buttonId == 1)
                            figs.add(new Rect(xAtual - defaultW / 2, yAtual - defaultH/ 2, defaultW, defaultH, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        else if(buttonId == 2)
                            figs.add(new Superelipse(xAtual - defaultW / 2, yAtual - defaultH/ 2, defaultW, defaultH, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
                        else if(buttonId == 3)
                            figs.add(new Ellipse(xAtual - defaultW / 2, yAtual - defaultH/ 2, defaultW, defaultH, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)) );
                        else if(buttonId == 4)
                            figs.add(new Triangulo(xAtual - defaultW / 2, yAtual - defaultH/ 2, defaultW, defaultH, new Color(r_line, g_line, b_line), new Color(r_back, g_back, b_back)));
                            
                        buttonId = -1;
                        repaint();
                        return;
                    }
                    boolean flag1 = false;
                    boolean flag2 = false;
                    for (int i = 0; i < points.length; i++) {
                        if (points[i].contains(prevPt)) {
                            pos = i;
                            flag1 = true;
                                
                            //System.out.printf("point %d\n",pos);
                                
                            for(int j = 0; j < 3; j++){
                                lastPoints[j] = new Point2D.Double(points[j].getX(), points[j].getY());
                            }
                                
                            break;
                        }
                    }

                    ListIterator<Figures> li = figs.listIterator(figs.size());
                    Figures fig = null;
                    while(li.hasPrevious()){
                        fig = li.previous();
                            
                        if(fig.clicked(xAtual, yAtual)){
                            flag2 = true;
                            focus = fig;
                            int index = figs.indexOf(focus);
                            figs.remove(index);
                            figs.add(focus);
                            break;
                        }
                    }
                            
                    if(!flag1 && !flag2){
                        focus = null;
                    }
                    
                    repaint();
                }
                    
                public void mouseReleased(MouseEvent evt){
                    pos = -1;	
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
        mainToolbox.Show(g, buttonId);
        for (Figures fig: this.figs) {
            fig.paint(g, fig.equals(focus));
        }
        if(focus != null){
            desenharRectSuporte(g);
            miniRect.x = (focus.x - 4) + (focus.w + 10) - 8;
            miniRect.y = (focus.y - 5) + (focus.h + 10) - 8;
            miniRect.paint(g, true);
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