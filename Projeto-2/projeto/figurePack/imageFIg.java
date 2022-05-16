package figurePack;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class imageFIg extends Figures{
    int angulo, borderSize;
    private BufferedImage image;

    public void setImageURL(String imageURL) {
        try {
            image = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            System.out.println("Error: " + imageURL + " not found! " + e);
        }
    }

    public imageFIg (String imageURL, int x, int y, int w, int h, int borderSize, int angulo, Color colorLine, Color colorBack) {
        super(x, y, w, h, colorLine, colorBack);
        this.angulo = angulo;
        this.borderSize = borderSize;
        setImageURL(imageURL);
    }

    public void paint(Graphics g, boolean focus) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(Math.toRadians(angulo), x + w/2, y + h/2);
        g2d.drawImage(image, x, y, w, h, colorBack, null);
        g2d.setColor(colorLine);
        g2d.drawRect(x, y, w, h);
        g2d.dispose();
    }

}
