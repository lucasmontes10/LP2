package button_;


import ivisible.IVisible;
import figurePack.Figures;
import java.awt.*;

public class Button_ implements IVisible{
    static int SPC = 20;
    static int DIM = 40;
    static int PAD = 4;


    public int id;
    public Figures fig;
    
    public Button_ (int id, Figures fig){
        this.id = id;
        this.fig = fig;
        this.fig.x = PAD+SPC;
        this.fig.y = PAD+SPC + id*DIM;
        this.fig.w = DIM-PAD*2;
        this.fig.h = DIM-PAD*2;
    }

    public boolean clicked (int x, int y){
    	if( fig.h != -1){
        	return (x>=fig.x && x<=fig.x+fig.w && y>=fig.y && y<=fig.y+fig.h );
        }
        else{
        	return ( x>=(this.fig.x-(this.fig.w/2)) && x<=(this.fig.x+(this.fig.w/2)) && y>=this.fig.y && y<=this.fig.y+this.fig.w );
        }
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(focused ? Color.GRAY : Color.LIGHT_GRAY);
        g2d.fillRect(fig.x-8, fig.y-4, 48, 48);
        
        if( this.fig.h == -1)
        	this.fig.x = this.fig.x + 16;
	
        this.fig.paint(g, false);
    }
}
