import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rectangle extends DrawOption {
    private int x1, y1;
    private int x2, y2;

    @Override
    public void onPressed(int x, int y) {
        x1 = x;
        y1 = y;

    }

    public Rectangle(Graphics g) {
        
    }

    @Override
    public void onReleased(int x, int y) {
        // si la coordonnée du 2nd point est au dessus du 1er point
        // on inverse les coordonnées
        if (y < y1) {
            y2 = y1;
            y1 = y;
        } else {
            y2 = y;
        }
        if (x < x1) {
            x2 = x1;
            x1 = x;
        } else {
            x2 = x;
        }
        Graphics g = new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        // set fill color
        g.setColor(d.getFill_color());

        // valeur absolue
        int largeur = (int) Math.abs(x2 - x1);
        int hauteur = (int) Math.abs(y2 - y1);

        if (this.fillMode == 1) {
            g.fillRect((int) x1, (int) y1, largeur, hauteur);
        } else {
            g.drawRect((int) x1, (int) y1, largeur, hauteur);

        }
        step = 0;
        // this.mouseMode = 0; // revenir au mode classique

    }

}
